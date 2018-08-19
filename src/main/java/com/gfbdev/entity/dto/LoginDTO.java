package com.gfbdev.entity.dto;

import com.gfbdev.entity.User;

public class LoginDTO {

    private String providerId;

    private String userId;

    private String token;

    private User.Status status;

    public User.Status getStatus() {
        return status;
    }

    public void setStatus(User.Status status) {
        this.status = status;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginDTO(String providerId, String userId, String token) {
        this.providerId = providerId;
        this.userId = userId;
        this.token = token;
    }
}
