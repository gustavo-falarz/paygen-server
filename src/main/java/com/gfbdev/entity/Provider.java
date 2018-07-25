package com.gfbdev.entity;



import org.springframework.data.geo.Point;

import java.util.List;

/**
 * Created by Headtrap on 28/08/2017.
 */

public class Provider extends User {

    private Lobby lobby;

    private List<Transaction> sales;

    private List<Consumption> consumptions;

    private List<User> employees;

    private Point location;

    private Status status;

    private String banner;

    private Type type;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Transaction> getSales() {
        return sales;
    }

    public void setSales(List<Transaction> sales) {
        this.sales = sales;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    public enum Status{
        PENDING,
        ACTIVE
    }

    public enum Type{
        RESTAURANT,
        HAMBURGUER,
        PIZZA
    }
}
