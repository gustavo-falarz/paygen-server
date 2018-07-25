package com.gfbdev.repository;

import com.gfbdev.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Headtrap on 15/07/2017.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByCpf(String cpf);

}

