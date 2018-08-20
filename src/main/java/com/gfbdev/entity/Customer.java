package com.gfbdev.entity;


import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Headtrap on 15/07/2017.
 */

public class Customer extends User {

    private String cpf;

    @DBRef
    @Lazy
    private List<Transaction> purchases;

    private CheckedIn checkedIn;


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

    public CheckedIn getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(CheckedIn checkedIn) {
        this.checkedIn = checkedIn;
    }

}
