package com.gfbdev.repository;

import com.gfbdev.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByProviderId(String providerId);

    List<Transaction> findByDateBetween(Date start, Date end);

    List<Transaction> findByCustomerId(String customerId);

}
