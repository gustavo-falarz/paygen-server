package com.gfbdev.controller;

import com.gfbdev.entity.Response;
import com.gfbdev.session.LobbySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobby")
public class LobbyController {

    private final
    LobbySession lobbySession;

    @Autowired
    public LobbyController(LobbySession lobbySession) {
        this.lobbySession = lobbySession;
    }

    @GetMapping("/checkIn/{userId}/{providerId}")
    public Response checkin(@PathVariable String userId, @PathVariable String providerId) {
        return lobbySession.checkIn(userId, providerId);
    }

    @RequestMapping("/checkOut/{userId}/{providerId}")
    public Response checkout(@PathVariable String userId, @PathVariable String providerId) {
        return lobbySession.checkOut(userId, providerId);
    }

    @GetMapping("/getCustomer/{providerId}")
    public Response getCustomers(@PathVariable String providerId) {
        return lobbySession.getCustomers(providerId);
    }
}
