package com.gfbdev.session;

import com.gfbdev.entity.Product;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Headtrap on 15/07/2017.
 */
@Component
public class ProductSession {

    @Autowired
    ProductRepository repository;

    public Response listProducts() {
        return Response.ok(repository.findAll());
    }

    public Response addProduct(Product product) {
        return Response.ok(repository.save(product));
    }

    public Response findProduct(Product product) {
        try {
            return Response.ok(repository.findByName(product.getName()));
        } catch (Exception e) {
            return Response.error("Problemas ao localizar produto");
        }
    }
}
