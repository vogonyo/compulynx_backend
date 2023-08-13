package com.compulyx.backend.controllers;

import com.compulyx.backend.entities.Account;

import com.compulyx.backend.entities.Transaction;
import com.compulyx.backend.exceptions.AccountNotFoundException;
import com.compulyx.backend.exceptions.CustomerNotFoundException;
import com.compulyx.backend.exceptions.InsufficientBalanceException;
import com.compulyx.backend.repositories.AccountRepository;
import com.compulyx.backend.services.AccountService;
import com.compulyx.backend.vm.AccountDTO;
import com.compulyx.backend.vm.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountDTOs = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDTOs);
    }

    @GetMapping("/{customerId}/balance")
    public ResponseEntity<ApiResponse<Double>> getAccountBalance(@PathVariable String customerId) {
        double balance = accountService.getAccountBalance(customerId);

        if (balance >= 0) {
            ApiResponse<Double> response = new ApiResponse<>(true, "Balance retrieved successfully", balance);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Double> response = new ApiResponse<>(false, "Customer or account not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{customerId}/mini-statement")
    public ResponseEntity<List<Transaction>> getMiniStatement(@PathVariable String customerId) {
        List<Transaction> miniStatement = accountService.getMiniStatement(customerId);
        return ResponseEntity.ok(miniStatement);
    }

    @PostMapping("/{customerId}/deposit/{amount}")
    public ResponseEntity<ApiResponse<Double>> cashDeposit(@PathVariable String customerId, @PathVariable double amount) {
        double newBalance = accountService.depositCash(customerId, amount);
        ApiResponse<Double> response = new ApiResponse<>(true, "Deposit successful", newBalance);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{customerId}/withdrawal/{amount}")
    public ResponseEntity<ApiResponse<Double>> cashWithdrawal(@PathVariable String customerId, @PathVariable double amount) {
        double currentBalance = accountService.getAccountBalance(customerId);

        if (currentBalance >= amount) {
            double newBalance = accountService.withdrawCash(customerId, amount);
            ApiResponse<Double> response = new ApiResponse<>(true, "Withdrawal successful", newBalance);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Double> response = new ApiResponse<>(false, "Insufficient Funds", currentBalance);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/{customerId}/transfer/{recipientAccountId}/{amount}")
    public ResponseEntity<ApiResponse<Object>> transferFunds(
            @PathVariable String customerId,
            @PathVariable String recipientAccountId,
            @PathVariable double amount) {
        try {
            double newBalance = accountService.transferFunds(customerId, recipientAccountId, amount);
            ApiResponse<Object> response = new ApiResponse<>(true, "Transfer successful", newBalance);
            return ResponseEntity.ok(response);
        } catch (InsufficientBalanceException e) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Insufficient balance for transfer");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (CustomerNotFoundException | AccountNotFoundException e) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Customer or account not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



}
