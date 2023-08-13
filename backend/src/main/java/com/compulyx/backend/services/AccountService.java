package com.compulyx.backend.services;

import com.compulyx.backend.controllers.CustomerController;
import com.compulyx.backend.entities.Account;
import com.compulyx.backend.entities.Customer;
import com.compulyx.backend.entities.Transaction;
import com.compulyx.backend.entities.TransactionType;
import com.compulyx.backend.repositories.AccountRepository;
import com.compulyx.backend.repositories.CustomerRepository;
import com.compulyx.backend.repositories.TransactionRepository;
import com.compulyx.backend.vm.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOs = new ArrayList<>();

        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountId(account.getAccountId());
            accountDTO.setBalance(account.getBalance());
            accountDTOs.add(accountDTO);
        }

        return accountDTOs;
    }
    public double getAccountBalance(String customerId) {
        Account account = accountRepository.findByCustomerCustomerId(customerId);
        if (account != null) {
            return account.getBalance();
        }
        return 0.0;
    }

    public List<Transaction> getMiniStatement(String customerId) {
        return transactionRepository.findTop10ByCustomerCustomerIdOrderByTransactionDateDesc(customerId);
    }


    public double depositCash(String customerId, double amount) {
        Account account = accountRepository.findByCustomerCustomerId(customerId);

        log.info("Account: {}, id {}, balance {}", account, account.getAccountId(), account.getBalance());
        if (account != null) {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            accountRepository.save(account);

            // Create a new transaction entry for the deposit
            Optional<Customer> customerOptional = customerRepository.findByCustomerId(customerId);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();

                Transaction transaction = new Transaction();
                transaction.setCustomer(customer);
                transaction.setTransactionType(TransactionType.DEPOSIT);
                transaction.setTransactionDate(new Date()); // Set the transaction date here
                transaction.setAmount(amount);
                transactionRepository.save(transaction);

                return newBalance;
            } else {
                // Handle case where customer is not found
                // You might want to throw an exception or return an error message
                throw new RuntimeException("Customer not found for ID: " + customerId);
            }
        }
        return 0.0;
    }

    public double withdrawCash(String customerId, double amount) {
        Account account = accountRepository.findByCustomerCustomerId(customerId);
        if (account != null) {
            double currentBalance = account.getBalance();
            if (currentBalance >= amount) {
                double newBalance = currentBalance - amount;
                account.setBalance(newBalance);
                accountRepository.save(account);

                // Create a new transaction entry for the withdrawal
                Optional<Customer> customerOptional = customerRepository.findByCustomerId(customerId);
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();

                    Transaction transaction = new Transaction();
                    transaction.setCustomer(customer);
                    transaction.setTransactionType(TransactionType.WITHDRAWAL);
                    transaction.setTransactionDate(new Date()); // Set the transaction date here
                    transaction.setAmount(amount);
                    transactionRepository.save(transaction);

                    return newBalance;
                } else {
                    throw new RuntimeException("Customer not found for ID: " + customerId);
                }
            } else {
                throw new RuntimeException("Insufficient balance for withdrawal");
            }
        }
        return 0.0;
    }

    public double transferFunds(String customerId, String targetAccountId, double amount) {
        Account sourceAccount = accountRepository.findByCustomerCustomerId(customerId);
        Account targetAccount = accountRepository.findByAccountId(targetAccountId);

        if (sourceAccount != null && targetAccount != null) {
            double sourceBalance = sourceAccount.getBalance();

            if (sourceBalance >= amount) {
                double newSourceBalance = sourceBalance - amount;
                sourceAccount.setBalance(newSourceBalance);
                accountRepository.save(sourceAccount);

                double targetBalance = targetAccount.getBalance();
                double newTargetBalance = targetBalance + amount;
                targetAccount.setBalance(newTargetBalance);
                accountRepository.save(targetAccount);

                // Create a new transaction entry for the funds transfer
                Optional<Customer> customerOptional = customerRepository.findByCustomerId(customerId);
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();

                    Transaction transaction = new Transaction();
                    transaction.setCustomer(customer);
                    transaction.setTransactionType(TransactionType.TRANSFER_DEBIT);
                    transaction.setTransactionDate(new Date());
                    transaction.setAmount(amount);
                    transactionRepository.save(transaction);

                    // Create a new transaction entry for the credit to target account
                    Transaction targetTransaction = new Transaction();
                    targetTransaction.setCustomer(targetAccount.getCustomer());
                    targetTransaction.setTransactionType(TransactionType.TRANSFER_CREDIT);
                    targetTransaction.setTransactionDate(new Date()); // Set the transaction date
                    targetTransaction.setAmount(amount);
                    transactionRepository.save(targetTransaction);

                    return newSourceBalance;
                } else {
                    throw new RuntimeException("Customer not found for ID: " + customerId);
                }
            } else {
                throw new RuntimeException("Insufficient balance for transfer");
            }
        }
        return 0.0;
    }



}
