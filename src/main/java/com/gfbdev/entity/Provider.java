package com.gfbdev.entity;


import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Headtrap on 28/08/2017.
 */

public class Provider extends User {

    private Lobby lobby;

    @DBRef
    private List<Transaction> sales;

    private List<Consumption> consumptions;

    @DBRef
    private List<User> employees;

    @DBRef
    private List<Item> items;

    private Point location;

    private Status status;

    private ProviderInfo info;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ProviderInfo getInfo() {
        return info;
    }

    public void setInfo(ProviderInfo info) {
        this.info = info;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public List<Transaction> getSales() {
        return sales;
    }

    public void setSales(List<Transaction> sales) {
        this.sales = sales;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        PENDING,
        ACTIVE
    }

}
