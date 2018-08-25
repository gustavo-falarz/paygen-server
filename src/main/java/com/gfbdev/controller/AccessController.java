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

    @RequestMapping("/validateEmployee/{email}/{password}")
    public Response validateEmployee(@PathVariable("email") String email, @PathVariable("password") String password) {
        return accessSession.validateEmployee(email, password);
    }

    @RequestMapping("/googleSignin/{email}/{name}")
    public Response googleSignin(@PathVariable("email") String email, @PathVariable("name") String name) {
        return accessSession.googleSignin(email, name);
    }

    @RequestMapping("/changePassword/{userId}/{password}")
    public Response changePassword(@PathVariable("userId") String userId, @PathVariable("password") String password) {
        return accessSession.changePassword(userId, password);
    }

    @RequestMapping("/changeProviderPassword/{providerId}/{password}")
    public Response changeProviderPassword(@PathVariable("providerId") String providerId, @PathVariable("password") String password) {
        return accessSession.changeProviderPassword(providerId, password);
    }
}
