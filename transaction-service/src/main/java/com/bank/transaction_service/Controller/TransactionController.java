package com.bank.transaction_service.Controller;

import com.bank.transaction_service.Entity.Transaction;
import com.bank.transaction_service.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService TransactionService) {
        this.transactionService = TransactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction processedTransaction = transactionService.processTransaction(transaction);
        return new ResponseEntity<>(processedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        List<Transaction> history = transactionService.getAccountHistory(accountNumber);
        return ResponseEntity.ok(history);
    }
}