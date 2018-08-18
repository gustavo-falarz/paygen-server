package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.*;
import com.gfbdev.entity.dto.ProviderLoginDTO;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.repository.EmployeeRepository;
import com.gfbdev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@SuppressWarnings("ALL")
@Component
public class AccessSession {

    final private CustomerSession customerSession;

    final private CustomerRepository customerRepository;

    final private ProviderRepository providerRepository;

    final private EmployeeSession employeeSession;

    final private EmployeeRepository employeeRepository;

    final private ProviderSession providerSession;

    @Autowired
    public AccessSession(CustomerSession customerSession, CustomerRepository customerRepository,
                         ProviderRepository providerRepository,
                         EmployeeSession employeeSession, EmployeeRepository employeeRepository, ProviderSession providerSession) {
        this.customerSession = customerSession;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.employeeSession = employeeSession;
        this.employeeRepository = employeeRepository;
        this.providerSession = providerSession;
    }

    public Response validateCustomer(String email, String password) {
        try {
            Response responseCustomer = customerSession.findCustomerByEmail(email);
            if (!responseCustomer.status) {
                return responseCustomer;
            }

            Customer customer = (Customer) responseCustomer.data;
            if (customer.getPassword().equals(password)) {
                customer.setToken(getToken());
                customerRepository.save(customer);
                return Response.ok(customer.getToken());
            } else {
                return Response.error(Messages.getInstance()
                        .getString("messages.error.invalid-password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response validateEmployee(String email, String password) {
        try {
            Response employeeResponse = employeeSession.findEmployeeByEmail(email);
            if (!employeeResponse.status) {
                return employeeResponse;
            }
            Employee employee = (Employee) employeeResponse.data;

            if (employee.getPassword().equals(password)) {
                employee.setToken(getToken());
                employeeRepository.save(employee);
                ProviderLoginDTO dto = new ProviderLoginDTO(employee.getProviderId(), employee.getId(), employee.getToken());
                return Response.ok(dto);
            } else {
                return Response.error(Messages.getInstance().getString("messages.error.invalid-password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response validateProvider(String email, String password) {
        try {
            Response responseProvider = providerSession.findProviderByEmail(email);
            if (!responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;
            if (provider.getPassword().equals(password)) {
                provider.setToken(getToken());
                providerRepository.save(provider);
                ProviderLoginDTO dto = new ProviderLoginDTO(provider.getId(), "", provider.getToken());
                return Response.ok(dto);
            } else {
                return Response.error(Messages.getInstance().getString("messages.error.invalid-password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
