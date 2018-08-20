package com.gfbdev.controller;

import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.entity.dto.ImagesDTO;
import com.gfbdev.session.ProviderSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final
    ProviderSession providerSession;

    @Autowired
    public ProviderController(ProviderSession providerSession) {
        this.providerSession = providerSession;
    }

    @GetMapping
    public Response findAll() {
        return providerSession.findAll();
    }

    @RequestMapping("/addProvider")
    public Response addProvider(@RequestBody Provider provider) {
        return providerSession.addProvider(provider);
    }

    @RequestMapping("/findProvider/{id}")
    public Response findProvider(@PathVariable String id) {
        return providerSession.findProvider(id);
    }

    @RequestMapping("/listEmployees/{providerId}")
    public Response listEmployees(@PathVariable("providerId") String providerId) {
        return providerSession.listEmployees(providerId);
    }

    @RequestMapping("/getImages/{providerId}")
    public Response getImages(@PathVariable("providerId") String providerId){
        return providerSession.getImages(providerId);
    }

    @RequestMapping("/setImages/{providerId}")
    public Response setImages(@PathVariable("providerId") String providerId, @RequestBody ImagesDTO dto){
        return providerSession.setImages(providerId, dto);
    }

}
