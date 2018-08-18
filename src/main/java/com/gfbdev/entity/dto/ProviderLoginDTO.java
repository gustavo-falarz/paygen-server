package com.gfbdev.entity.dto;

public class ProviderLoginDTO {

    private String providerId;

    private String userId;

    private String token;

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

    public ProviderLoginDTO(String providerId, String userId, String token) {
        this.providerId = providerId;
        this.userId = userId;
        this.token = token;
    }
}
