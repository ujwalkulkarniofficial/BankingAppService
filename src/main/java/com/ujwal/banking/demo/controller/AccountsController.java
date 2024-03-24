package com.ujwal.banking.demo.controller;

import com.ujwal.banking.demo.exceptions.ResourceNotFoundException;
import com.ujwal.banking.demo.model.Account;
import com.ujwal.banking.demo.services.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    @Autowired
    AccountDetailsService accountDetailsService;

    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccounts() {
        return accountDetailsService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") UUID accId)
            throws ResourceNotFoundException {
        Account user = accountDetailsService.getAccountById(accId);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable(value = "id") UUID userId)
            throws ResourceNotFoundException {
        Account acc = accountDetailsService.getAccountByUserId(userId);
        return ResponseEntity.ok().body(acc);
    }


}
