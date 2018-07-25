package com.gfbdev.entity.dto;

import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Provider;
import com.gfbdev.entity.Transaction;

public class TransactioDTO {

    private Transaction transaction;
    private Customer customer;
    private Provider provider;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
