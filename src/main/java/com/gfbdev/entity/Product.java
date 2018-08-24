package com.gfbdev.entity;


/**
 * Created by Headtrap on 15/07/2017.
 */

public class Product extends Item {
    private long amount;

    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long ammount) {
        this.amount = ammount;
    }

}
