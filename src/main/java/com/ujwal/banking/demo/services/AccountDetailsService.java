package com.ujwal.banking.demo.services;

import com.ujwal.banking.demo.exceptions.ResourceNotFoundException;
import com.ujwal.banking.demo.model.Account;
import com.ujwal.banking.demo.model.User;
import com.ujwal.banking.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountDetailsService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(UUID accId) throws ResourceNotFoundException {
        return accountRepository.findByAccountId(accId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accId));
    }

    public Account getAccountByUserId(UUID userId) {
       return accountRepository.findByUserId(userId);
    }

    public Account createNewAccount(User user) {
        Account account = new Account();
        account.setUser(user);
        return accountRepository.saveAndFlush(account);
    }

}
