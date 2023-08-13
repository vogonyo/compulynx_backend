package com.compulyx.backend.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super("Customer not found for ID: " + customerId);
    }
}
