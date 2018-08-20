package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.*;
import com.gfbdev.entity.CheckedIn;
import com.gfbdev.entity.dto.LoginDTO;
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
    public AccessSession(CustomerSession customerSession,
                         CustomerRepository customerRepository,
                         ProviderRepository providerRepository,
                         EmployeeSession employeeSession,
                         EmployeeRepository employeeRepository,
                         ProviderSession providerSession) {
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
                LoginDTO dto = new LoginDTO("", customer.getId(), customer.getToken());
                dto.setStatus(customer.getStatus());
                return Response.ok(dto);
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
                LoginDTO dto = new LoginDTO(employee.getProviderId(), employee.getId(), employee.getToken());
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
                LoginDTO dto = new LoginDTO(provider.getId(), "", provider.getToken());
                return Response.ok(dto);
            } else {
                return Response.error(Messages.getInstance().getString("messages.error.invalid-password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response changePassword(String userId, String password) {
        try {
            if (password.length() < 6) {
                return Response.error(Messages.getInstance().getString("messages.error.password-too-short"));
            }
            Response responseCustomer = customerSession.findCustomer(userId);
            if (!responseCustomer.status) {
                return responseCustomer;
            }
            Customer customer = (Customer) responseCustomer.data;
            customer.setPassword(password);
            customer.setCheckedIn(new CheckedIn("", "", new ProviderInfo()));
            customer.setStatus(User.Status.ACTIVE);
            return Response.ok(Messages.getInstance().getString("messages.success.password-changed"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response googleSignin(String email, String name) {
        try {
            Customer customer = customerRepository.findByEmail(email);
            if (customer == null) {
                customer = new Customer();
                customer.setEmail(email);
                customer.setName(name);
            }
            customer.setStatus(User.Status.ACTIVE);
            customer.setToken(getToken());
            String id = customerRepository.save(customer).getId();
            return Response.ok(new LoginDTO("", id, customer.getToken()));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
