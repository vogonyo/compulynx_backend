package com.compulyx.backend.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountId) {
        super("Account not found for ID: " + accountId);
    }
}
