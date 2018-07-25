package com.gfbdev.controller;

import com.gfbdev.entity.Product;
import com.gfbdev.entity.Response;
import com.gfbdev.session.ProductSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Headtrap on 15/07/2017.
 */
@RestController
@RequestMapping("/product")
public class ProductController{
    private final
    ProductSession session;

    @Autowired
    public ProductController(ProductSession session) {
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response getUsers() {
        return session.listProducts();
    }

    @RequestMapping("/addProduct")
    public Response addProduct(@RequestBody Product product){
        return session.addProduct(product);
    }

    @RequestMapping("/findProduct")
    public Response findProduct(@RequestBody Product product){
        return session.findProduct(product);
    }
}
