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
@RequestMapping("/comsuption")
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

    @RequestMapping("/additem")
    public Response additem(@RequestBody ConsumptionDTO dto) {
        return session.addItem(dto);
    }

    @RequestMapping("/removeItem")
    public Response removeItem(@RequestBody ConsumptionDTO dto) {
        return session.removeItem(dto);
    }
}
