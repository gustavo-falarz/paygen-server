package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Employee;
import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.EmployeeRepository;
import com.gfbdev.repository.ProviderRepository;
import com.gfbdev.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSession {

    private final EmployeeRepository employeeRepository;

    private final ProviderSession providerSession;

    private final ProviderRepository providerRepository;

    @Autowired
    public EmployeeSession(EmployeeRepository employeeRepository, ProviderSession providerSession, ProviderRepository providerRepository) {
        this.employeeRepository = employeeRepository;
        this.providerSession = providerSession;
        this.providerRepository = providerRepository;
    }

    public Response findEmployeeByEmail(String email) {
        try {
            Employee employee = employeeRepository.findByEmail(email);
            if (employee == null) {
                return Response.error(Messages.getInstance()
                        .getString("message.error.employee-not-found"));
            } else {
                return Response.ok(employee);
            }
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    public Response addEmployee(Employee employee) {
        try {
            Response responseProvider = providerSession.findProvider(employee.getProviderId());
            if (!responseProvider.status) {
                return responseProvider;
            }

            Provider provider = (Provider) responseProvider.data;
            if (provider.getEmployees().contains(employee)) {
                provider.getEmployees().remove(employee);
            }

            Employee definitive = employee;
            if (employee.getId() == null || employee.getId().equals("")) {
                definitive = build(employee);
            }
            employeeRepository.save(definitive);
            provider.getEmployees().add(definitive);
            providerRepository.save(provider);
            return Response.ok(Messages.getInstance().getString("message.success.employee-registered"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    private Employee build(Employee employee) {
        Employee building = new Employee();
        employee.setProviderId(employee.getProviderId());
        building.setName(employee.getName());
        building.setEmail(employee.getEmail());
        building.setPassword(Password.getInstance().getEncryptor().encryptPassword(employee.getPassword()));
        building.setToken(employee.getToken());
        building.setRole(employee.getRole());
        return building;
    }

}
