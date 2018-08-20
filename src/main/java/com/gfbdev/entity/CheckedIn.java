package com.gfbdev.entity;

public class CheckedIn {

    private String id;

    private String name;

    private ProviderInfo info;

    public CheckedIn(String id, String name, ProviderInfo info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public ProviderInfo getInfo() {
        return info;
    }

    public void setInfo(ProviderInfo info) {
        this.info = info;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
