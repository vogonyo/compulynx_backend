package com.compulyx.backend.entities;



public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    TRANSFER_CREDIT("Transfer Credit"),
    TRANSFER_DEBIT("Transfer Debit");

    private final String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


