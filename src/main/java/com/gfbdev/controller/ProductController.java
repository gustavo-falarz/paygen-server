package com.gfbdev.controller;

import com.gfbdev.entity.Product;
import com.gfbdev.entity.Response;
import com.gfbdev.session.ProductSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Headtrap on 15/07/2017.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final
    ProductSession session;

    @Autowired
    public ProductController(ProductSession session) {
        this.session = session;
    }

    @RequestMapping("listProducts/{providerId}")
    public Response getItems(@PathVariable("providerId") String providerId) {
        return session.listProducts(providerId);
    }

    @RequestMapping("/addProduct/{providerId}")
    public Response addProduct(@RequestBody Product product, @PathVariable("providerId") String providerId) {
        return session.addProduct(providerId, product);
    }

    @RequestMapping("/findProduct/{providerId}/{query}")
    public Response findProduct(@PathVariable("providerId") String providerId, @PathVariable("query") String query) {
        return session.findProduct(providerId, query);
    }
}
