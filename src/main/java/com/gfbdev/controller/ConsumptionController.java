package com.gfbdev.controller;


import com.gfbdev.entity.Item;
import com.gfbdev.entity.Response;
import com.gfbdev.entity.dto.ConsumptionDTO;
import com.gfbdev.session.ConsumptionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/addItem/{providerId}/{customerId}")
    public Response additem(@RequestBody Item item,
                            @PathVariable("providerId") String providerId,
                            @PathVariable("customerId") String customerId) {
        return session.addItem(providerId, customerId, item);
    }

    @RequestMapping("/removeItem")
    public Response removeItem(@RequestBody ConsumptionDTO dto) {
        return session.removeItem(dto);
    }
}
