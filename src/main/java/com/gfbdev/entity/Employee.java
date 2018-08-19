package com.gfbdev.entity;

import javafx.util.Builder;

import java.util.List;

public class Employee extends User {

    private String providerId;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
