package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Response;
import com.gfbdev.entity.CheckedIn;
import com.gfbdev.repository.CustomerRepository;
import com.gfbdev.utils.Constants;
import com.gfbdev.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.gfbdev.Messages.*;
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
            Customer existingCustomer = repository.findByEmail(customer.getEmail());
            if (existingCustomer != null) {
                return Response.error(getInstance().getString("messages.error.provider-already-registered"));
            }
            customer.setStatus(Customer.Status.PENDING);
            customer.setCheckedIn(new CheckedIn("", ""));
            customer.setPassword(StringUtils.generateRandomCode());
            customer.setPurchases(new ArrayList<>());
            String message = String.format(Constants.MESSAGE_ACCOUNT_ACTIVATION, customer.getPassword());
            repository.save(customer);
            sendEmailNewUser(customer.getEmail(), message);
            return Response.ok(Messages.getInstance().getString("messages.success.customer-registered"));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(getInstance().getString("messages.error.provider-problems-saving-user"));
        }
    }

    public Response findCustomerByEmail(String email) {
        try {
            Customer customer = repository.findByEmail(email);
            if (customer == null) {
                return Response.error(getInstance().getString("messages.error.customer-not-found"));
            } else {
                return Response.ok(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    public Response findCustomer(String userId) {
        try {
            Customer customer = repository.findOne(userId);
            if (customer == null) {
                return Response.error(getInstance().getString("messages.error.customer-not-found"));
            } else {
                return Response.ok(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
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

    public Response checkReception(String customerId) {
        try {
            Response customerResponse = findCustomer(customerId);
            if (!customerResponse.status) {
                return Response.error(getInstance().getString("messages.error.customer-not-found"));
            }

            Customer customer = (Customer) customerResponse.data;
            return Response.ok(customer.getCheckedIn());

        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }
}
