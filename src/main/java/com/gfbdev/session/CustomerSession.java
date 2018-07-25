package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.utils.Constants;
import com.gfbdev.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.gfbdev.service.EmailService.sendEmailNewUser;

/**
 * Created by Headtrap on 15/07/2017.
 */

@Component
public class CustomerSession {

    @Autowired
    CustomerRepository repository;

    public Response addCustomer(Customer customer) {
        try {
            Customer existingCustomer = repository.findOne(customer.getEmail());
            if (existingCustomer != null) {
                return Response.error(Messages.getInstance().getString("messages.error.provider-already-registered"));
            }
            customer.setStatus(Customer.Status.PENDING);
            customer.setPassword(StringUtils.generateRandomCode());
            String message = String.format(Constants.MESSAGE_ACCOUNT_ACTIVATION, customer.getPassword());
            sendEmailNewUser(customer.getEmail(), message);
            return Response.ok(repository.save(customer));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(Messages.getInstance().getString("messages.error.provider-problems-saving-user"));
        }
    }

    public Response findCustomer(String userId) {
        try {
            Customer customer = repository.findOne(userId);
            if (customer == null) {
                return Response.error(Messages.getInstance().getString("messages.error.customer-not-found"));
            } else {
                return Response.ok(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("messages.error.problems-searching-user");
        }
    }

    public Response updateCustomer(Customer customer) {
        if (customer.getId() != null) {
            try {
                return Response.ok(repository.save(customer));
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("Problemas ao atualizar usuário");
            }
        } else {
            return Response.error("Necessário informar o ID do usuário");
        }
    }

    public Response listAllCustomers() {
        try {
            return Response.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    //TODO Change password
}
