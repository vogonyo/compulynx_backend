package com.compulyx.backend.repositories;

import com.compulyx.backend.entities.Account;
import com.compulyx.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomer(Customer customer);

    Account findByCustomerCustomerId(String customerId);

    Account findByAccountId(String accountId);

    // Method to retrieve all accounts
    List<Account> findAll();

}