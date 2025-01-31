package org.example.service;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumberVulnerable(accountNumber);
    }

    // Sensitive Data Exposure Through Error Messages
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id + ". Database query: SELECT * FROM Account WHERE id = " + id));
    }

    // Insecure Bean Validation
    public Account createAccount(Account account) {
        if (account.getAccountNumber().length() > 1000) {
            throw new RuntimeException("Invalid account number length: " + account.getAccountNumber());
        }
        return accountRepository.save(account);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}