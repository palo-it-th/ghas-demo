package org.example.repository;

import org.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Vulnerable to SQL Injection
    @Query(value = "SELECT * FROM Account WHERE accountNumber = '" + ":accountNumber" + "'", nativeQuery = true)
    List<Account> findByAccountNumberVulnerable(@Param("accountNumber") String accountNumber);
}