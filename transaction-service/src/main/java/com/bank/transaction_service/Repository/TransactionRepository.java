package com.bank.transaction_service.Repository;

import com.bank.transaction_service.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Finds all transactions involving a specific account (either as source or destination)
    List<Transaction> findBySourceAccountOrDestinationAccountOrderByTimestampDesc(String sourceAccount, String destinationAccount);
}