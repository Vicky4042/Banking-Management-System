package com.bank.transaction_service.Service;

import com.bank.transaction_service.Entity.Transaction;
import com.bank.transaction_service.Entity.TransactionType;
import com.bank.transaction_service.Repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    // Constructor injection
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction processTransaction(Transaction transaction) {
        // Adjusting account records depending on the transaction type
        if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
            transaction.setDestinationAccount("ATM/CASH-OUT");
        } else if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
            transaction.setSourceAccount("CASH-IN");
        }

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAccountHistory(String accountNumber) {
        return transactionRepository.findBySourceAccountOrDestinationAccountOrderByTimestampDesc(accountNumber, accountNumber);
    }
}