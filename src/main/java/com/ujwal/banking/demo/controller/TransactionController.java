package com.ujwal.banking.demo.controller;

import com.ujwal.banking.demo.model.TransactionRequest;
import com.ujwal.banking.demo.services.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    TransactionsService transactionsService;

    @PostMapping("/deposit")
    public ResponseEntity registerUser(@Valid @RequestBody TransactionRequest transactionRequest) {
        try {
            var ret = transactionsService.depositIntoAccount(transactionRequest.getAccountId(), transactionRequest.getAmount(), transactionRequest.getRemarks());
            return ResponseEntity.ok().body(ret);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdrawAmount(@Valid @RequestBody TransactionRequest transactionRequest) {
        try {
            var ret = transactionsService.withdrawFromAccount(transactionRequest.getAccountId(), transactionRequest.getAmount(), transactionRequest.getRemarks());
            return ResponseEntity.ok().body(ret);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity getTxnByAccId(@PathVariable(value = "id") UUID accId){
        try {
            var ret = transactionsService.getAllTransaction(accId);
            return ResponseEntity.ok().body(ret);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
