package com.gfbdev.repository;

import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Headtrap on 15/07/2017.
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);
}
