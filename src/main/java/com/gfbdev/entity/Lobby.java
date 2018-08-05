package com.gfbdev.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Lobby {

    @DBRef
    private List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
