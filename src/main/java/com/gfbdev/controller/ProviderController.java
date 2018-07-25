package com.gfbdev.controller;

import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Response;
import com.gfbdev.session.ProviderSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final
    ProviderSession providerSession;

    @Autowired
    public ProviderController(ProviderSession providerSession) {
        this.providerSession = providerSession;
    }

    @RequestMapping("/addProvider")
    public Response addProvider(@RequestBody Provider provider) {
        return providerSession.addProvider(provider);
    }

    @RequestMapping("/findProvider/{id}")
    public Response findProvider(@PathVariable String id) {
        return providerSession.findProvider(id);
    }

}
