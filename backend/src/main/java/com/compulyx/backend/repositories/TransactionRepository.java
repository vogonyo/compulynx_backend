package com.compulyx.backend.repositories;

import com.compulyx.backend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(Long transactionId);

    List<Transaction> findTop10ByCustomerCustomerIdOrderByTransactionDateDesc(String customerId);

}