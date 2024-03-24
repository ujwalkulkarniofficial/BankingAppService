package com.ujwal.banking.demo.constants;

public enum TransactionType {
    DEBIT("DEBIT"),
    CREDIT("CREDIT");

    public String getValue() {
        return value;
    }

    private final String value;

    private TransactionType(String value) {
        this.value = value;
    }
}
