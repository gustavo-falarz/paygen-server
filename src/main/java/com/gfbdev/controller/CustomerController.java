package com.gfbdev.controller;

import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Response;
import com.gfbdev.session.CustomerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Headtrap on 15/07/2017.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final
    CustomerSession session;

    @Autowired
    public CustomerController(CustomerSession session) {
        this.session = session;
    }

    @RequestMapping("findCustomer/{cpf}")
    public Response findCustomer(@PathVariable String cpf) {
        return session.findCustomer(cpf);
    }

    @RequestMapping("/updateCustomer")
    public Response updateCustomer(@RequestBody Customer customer) {
        return session.updateCustomer(customer);
    }

    @RequestMapping("/listAllCustomers")
    public Response listAllCustomers() {
        return session.listAllCustomers();
    }

    @RequestMapping("/checkReception/{customerId}")
    public Response checkReception(@PathVariable String customerId) {
        return session.checkReception(customerId);
    }

}
