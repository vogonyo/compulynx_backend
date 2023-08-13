package com.compulyx.backend.vm;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T balance;

    public ApiResponse(boolean success, String message, T balance) {
        this.success = success;
        this.message = message;
        this.balance = balance;
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters and setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBalance() {
        return balance;
    }

    public void setBalance(T balance) {
        this.balance = balance;
    }
}
