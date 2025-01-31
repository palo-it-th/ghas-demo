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

    // Sensitive Data Exposure (vulnerability)
    public void logAccountDetails(Account account) {
        System.out.println("Account Details: " + account); // Logging sensitive data
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}