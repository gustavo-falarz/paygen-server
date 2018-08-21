package com.gfbdev.entity;

public class Delivery extends Transaction {

    private StatusDelivery statusDelivery;

    private String observation;

    public StatusDelivery getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(StatusDelivery statusDelivery) {
        this.statusDelivery = statusDelivery;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public enum StatusDelivery{
        DELIVERED,
        PENDING
    }


}
