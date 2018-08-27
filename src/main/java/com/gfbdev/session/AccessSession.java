package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.*;
import com.gfbdev.entity.dto.LoginDTO;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.repository.EmployeeRepository;
import com.gfbdev.repository.ProviderRepository;
import com.gfbdev.repository.UserRepository;
import com.gfbdev.utils.Constants;
import com.gfbdev.utils.Password;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.gfbdev.utils.Constants.USER_PIC_PLACE_HOLDER;

@SuppressWarnings("ALL")
@Component
public class AccessSession {

    final private CustomerSession customerSession;

    final private CustomerRepository customerRepository;

    final private ProviderRepository providerRepository;

    final private EmployeeSession employeeSession;

    final private EmployeeRepository employeeRepository;

    final private ProviderSession providerSession;

    final private UserRepository userRepository;

    @Autowired
    public AccessSession(CustomerSession customerSession,
                         CustomerRepository customerRepository,
                         ProviderRepository providerRepository,
                         EmployeeSession employeeSession,
                         EmployeeRepository employeeRepository,
                         ProviderSession providerSession, UserRepository userRepository) {
        this.customerSession = customerSession;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.employeeSession = employeeSession;
        this.employeeRepository = employeeRepository;
        this.providerSession = providerSession;
        this.userRepository = userRepository;
    }

    public Response validateCustomer(String email, String password) {
        try {
            Response responseCustomer = customerSession.findCustomerByEmail(email);
            if (!responseCustomer.status) {
                return responseCustomer;
            }

            Customer customer = (Customer) responseCustomer.data;
            StrongPasswordEncryptor encryptor = Password.getInstance().getEncryptor();
            if (encryptor.checkPassword(password, customer.getPassword())) {
                customer.setToken(getToken());
                customerRepository.save(customer);
                LoginDTO dto = new LoginDTO(
                        "",
                        customer.getId(),
                        customer.getToken(),
                        customer.getName(),
                        customer.getPicture());

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
            StrongPasswordEncryptor encryptor = Password.getInstance().getEncryptor();
            ;
            if (encryptor.checkPassword(password, employee.getPassword())) {
                employee.setToken(getToken());
                employeeRepository.save(employee);
                LoginDTO dto = new LoginDTO(
                        employee.getProviderId(),
                        employee.getId(),
                        employee.getToken(),
                        USER_PIC_PLACE_HOLDER,
                        employee.getName());

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
            StrongPasswordEncryptor encryptor = Password.getInstance().getEncryptor();
            if (encryptor.checkPassword(password, provider.getPassword())) {
                provider.setToken("provider" + getToken());
                providerRepository.save(provider);
                LoginDTO dto = new LoginDTO(
                        provider.getId(),
                        provider.getId(),
                        provider.getToken(),
                        provider.getInfo().getLogo(),
                        provider.getName());

                dto.setStatus(provider.getStatus());

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

            StrongPasswordEncryptor encryptor = Password.getInstance().getEncryptor();
            String encrypted = encryptor.encryptPassword(password);
            customer.setPassword(encrypted);
            customer.setStatus(User.Status.ACTIVE);
            customerRepository.save(customer);
            return Response.ok(Messages.getInstance().getString("messages.success.password-changed"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response changeProviderPassword(String providerId, String password) {
        try {
            if (password.length() < 6) {
                return Response.error(Messages.getInstance().getString("messages.error.password-too-short"));
            }

            Response responseProvider = providerSession.findProvider(providerId);
            if (!responseProvider.status) {
                return responseProvider;
            }
            Provider provider = (Provider) responseProvider.data;

            StrongPasswordEncryptor encryptor = Password.getInstance().getEncryptor();
            String encrypted = encryptor.encryptPassword(password);
            provider.setPassword(encrypted);
            provider.setStatus(User.Status.ACTIVE);
            providerRepository.save(provider);

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
                customer.setPicture(Constants.USER_PIC_PLACE_HOLDER);
            }
            customer.setStatus(User.Status.ACTIVE);
            customer.setToken(getToken());
            String id = customerRepository.save(customer).getId();
            return Response.ok(new LoginDTO(
                    "",
                    id,
                    customer.getToken(),
                    customer.getPicture(),
                    customer.getName()));

        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response validateToken(String token) {
        boolean result = false;
        if (token.startsWith("provider")) {
            result = providerRepository.existsByToken(token);
        } else {
            result = customerRepository.existsByToken(token);
        }
        if (result) {
            return Response.ok("Token válido");
        } else {
            return Response.error("Acesso inválido");
        }
    }

    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
