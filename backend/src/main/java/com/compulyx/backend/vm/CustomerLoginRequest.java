package com.compulyx.backend.vm;

public class CustomerLoginRequest {

    private String customerId;

    private String pin;

    public CustomerLoginRequest() {
    }

    public CustomerLoginRequest(String customerId, String pin) {
        this.customerId = customerId;
        this.pin = pin;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
