package com.ujwal.banking.demo.repository;

import com.ujwal.banking.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select acc from Account acc where acc.user.userId = ?1")
    Account findByUserId(UUID userId);

    Optional<Account> findByAccountId(UUID accId);
}
