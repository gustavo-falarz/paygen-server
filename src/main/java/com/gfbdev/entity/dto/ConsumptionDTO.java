package com.gfbdev.entity.dto;

import com.gfbdev.entity.Customer;
import com.gfbdev.entity.Item;
import com.gfbdev.entity.Provider;

import java.util.List;

public class ConsumptionDTO {

    private Customer customer;

    private Provider provider;

    private List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
