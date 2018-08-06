package com.gfbdev.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Transaction {

    @Id
    private String purchaseId;

    private List<Item> items;

    private double total;

    private double discount;

    private PaymentMethod paymentMethod;

    private Status status;

    private String customerId;

    private String providerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT,
        CASH

    }

    public enum Status {
        PAID,
        PENDING,
        CANCELED,
        OPEN
    }


}
