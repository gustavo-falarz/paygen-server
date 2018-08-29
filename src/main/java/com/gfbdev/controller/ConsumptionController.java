package com.gfbdev.controller;


import com.gfbdev.entity.Response;
import com.gfbdev.entity.dto.ConsumptionDTO;
import com.gfbdev.session.ConsumptionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

    private final
    ConsumptionSession session;

    @Autowired
    public ConsumptionController(ConsumptionSession session) {
        this.session = session;
    }

    @RequestMapping("/getConsumption/{userId}/{providerId}")
    public Response getConsumption(@PathVariable("userId") String userId, @PathVariable("providerId") String providerId) {
        return session.getConsumption(userId, providerId);
    }

    @RequestMapping("/addItem/{providerId}/{customerId}/{itemId}")
    public Response additem(@PathVariable("itemId") String itemId,
                            @PathVariable("providerId") String providerId,
                            @PathVariable("customerId") String customerId) {
        return session.addItem(providerId, customerId, itemId);
    }

    @RequestMapping("/removeItem/{providerId}/{customerId}/{itemId}")
    public Response removeItem(@PathVariable("providerId") String providerId,
                               @PathVariable("customerId") String customerId,
                               @PathVariable("itemId") String itemId) {
        return session.removeItem(providerId, customerId, itemId);
    }
}
