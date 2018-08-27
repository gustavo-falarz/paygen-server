package com.gfbdev.repository;

import com.gfbdev.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByProviderIdOrderByDateDesc(String providerId);

    List<Transaction> findByDateBetweenAndProviderIdOrderByDateDesc(Date start, Date end, String providerId);

    List<Transaction> findByDateBetweenAndCustomerIdOrderByDateDesc(Date start, Date end, String customerId);

    List<Transaction> findByCustomerIdOrderByDateDesc(String customerId);

}
