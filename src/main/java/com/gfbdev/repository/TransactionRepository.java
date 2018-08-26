package com.gfbdev.repository;

import com.gfbdev.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByProviderId(String providerId);

    List<Transaction> findByDateBetweenAndProviderId(Date start, Date end, String providerId);

    List<Transaction> findByDateBetweenAndCustomerId(Date start, Date end, String customerId);

    List<Transaction> findByCustomerId(String customerId);

}
