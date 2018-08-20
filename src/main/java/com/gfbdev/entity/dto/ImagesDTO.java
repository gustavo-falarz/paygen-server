package com.gfbdev.entity.dto;

public class ImagesDTO {

    private String banner;

    private String logo;

    public ImagesDTO() {
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ImagesDTO(String banner, String logo) {
        this.banner = banner;
        this.logo = logo;
    }
}
