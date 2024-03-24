package com.ujwal.banking.demo.services;

import com.ujwal.banking.demo.constants.TransactionType;
import com.ujwal.banking.demo.exceptions.ResourceNotFoundException;
import com.ujwal.banking.demo.model.Account;
import com.ujwal.banking.demo.model.Transaction;
import com.ujwal.banking.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionsService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountDetailsService accountDetailsService;

    public Transaction depositIntoAccount(UUID account_id, double amount, String remarks) throws ResourceNotFoundException {
        Account account = accountDetailsService.getAccountById(account_id);
        account.setBalance(account.getBalance() + amount);
        Transaction txn = new Transaction();
        txn.setAmount(amount);
        txn.setRecipientAccount(account);
        txn.setTransactionType(TransactionType.CREDIT);
        txn.setRemarks(remarks);
        return transactionRepository.saveAndFlush(txn);
    }

    public Transaction withdrawFromAccount(UUID account_id, double amount, String remarks) throws ResourceNotFoundException {
        Account account = accountDetailsService.getAccountById(account_id);
        account.setBalance(account.getBalance() - amount);
        Transaction txn = new Transaction();
        txn.setAmount(amount);
        txn.setRecipientAccount(account);
        txn.setTransactionType(TransactionType.DEBIT);
        txn.setRemarks(remarks);
        return transactionRepository.saveAndFlush(txn);
    }

    public List<Transaction> getAllTransaction(UUID accountId) throws ResourceNotFoundException {
        Account account = accountDetailsService.getAccountById(accountId);
        List<Transaction> transactionList = transactionRepository.findByRecipientAccountOrderByTransactionTimestampDesc(account);
        return transactionList;
    }


}
