package com.gfbdev.entity;

import java.util.List;

public class ProviderInfo {

    private String address;

    private String about;

    private Type type;

    private String banner;

    private List<OpenHours> openHours;

    public List<OpenHours> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(List<OpenHours> openHours) {
        this.openHours = openHours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public enum Type{
        RESTAURANT,
        HAMBURGUER,
        PIZZA
    }
}
