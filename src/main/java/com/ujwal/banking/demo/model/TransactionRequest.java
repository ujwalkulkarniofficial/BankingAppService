package com.ujwal.banking.demo.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TransactionRequest {

    UUID accountId;
    double amount;
    String remarks;

}
