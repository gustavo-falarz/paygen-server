package com.gfbdev.repository;

import com.gfbdev.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Headtrap on 15/07/2017.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByEmail(String email);

    boolean existsByToken(String token);

}

