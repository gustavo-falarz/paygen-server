package com.gfbdev.entity;

public class Payment {

    private int amount;

    private String returnCode;

    private String serviceTaxAmount;

    private String type;

    private String receivedDate;

    private String country;

    private String tid;

    private String returnMessage;

    private String authenticate;

    private CreditCard creditCard;

    private String proofOfSale;

    private String recurrent;

    private String status;

    private String isSplitted;

    private String capture;

    private String authorizationCode;

    private String interest;

    private String softDescriptor;

    private int installments;

    private String currency;

    private String provider;

    private String paymentId;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(String serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getProofOfSale() {
        return proofOfSale;
    }

    public void setProofOfSale(String proofOfSale) {
        this.proofOfSale = proofOfSale;
    }

    public String getRecurrent() {
        return recurrent;
    }

    public void setRecurrent(String recurrent) {
        this.recurrent = recurrent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsSplitted() {
        return isSplitted;
    }

    public void setIsSplitted(String isSplitted) {
        this.isSplitted = isSplitted;
    }

    public String getCapture() {
        return capture;
    }

    public void setCapture(String capture) {
        this.capture = capture;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getSoftDescriptor() {
        return softDescriptor;
    }

    public void setSoftDescriptor(String softDescriptor) {
        this.softDescriptor = softDescriptor;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
