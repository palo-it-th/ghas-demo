package org.example.repository;

import org.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Vulnerable to SQL Injection
    @Query(value = "SELECT * FROM Account WHERE accountNumber = '" + ":accountNumber" + "'", nativeQuery = true)
    Account findByAccountNumber(String accountNumber);
}