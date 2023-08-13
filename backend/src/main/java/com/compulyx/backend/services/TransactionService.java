package com.compulyx.backend.services;

import com.compulyx.backend.entities.Transaction;
import com.compulyx.backend.exceptions.TransactionNotFoundException;
import com.compulyx.backend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction getTransactionDetailsById(Long transactionId) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);

        if (optionalTransaction.isPresent()) {
            return optionalTransaction.get();
        } else {
            throw new TransactionNotFoundException("Transaction not found with ID: " + transactionId);
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
