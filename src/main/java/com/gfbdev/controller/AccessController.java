package com.gfbdev.controller;

import com.gfbdev.entity.Response;
import com.gfbdev.session.AccessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
public class AccessController {

    private final AccessSession accessSession;

    @Autowired
    public AccessController(AccessSession accessSession) {
        this.accessSession = accessSession;
    }

    @RequestMapping("/validateCustomer/{email}/{password}")
    public Response validateCustomer(@PathVariable("email") String email, @PathVariable("password") String password) {
        return accessSession.validateCustomer(email, password);
    }

    @RequestMapping("/validateProvider/{email}/{password}")
    public Response validateProvider(@PathVariable("email") String email, @PathVariable("password") String password) {
        return accessSession.validateProvider(email, password);
    }
    @RequestMapping("/validateProvider/{email}/{password}")
    public Response validateEmployee(@PathVariable("email") String email, @PathVariable("password") String password) {
        return accessSession.validateEmployee(email, password);
    }

}
