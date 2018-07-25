package com.gfbdev.entity;


import java.util.List;

/**
 * Created by Headtrap on 15/07/2017.
 */

public class Customer extends User {

    private String cpf;

    private List<Transaction> purchases;

    private List<Consumption> consumption;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Consumption> getConsumption() {
        return consumption;
    }

    public void setConsumption(List<Consumption> consumption) {
        this.consumption = consumption;
    }

    public List<Transaction> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Transaction> purchases) {
        this.purchases = purchases;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public enum Status{
        ACTIVE,
        PENDING
    }
}
