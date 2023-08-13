package com.compulyx.backend.repositories;

import com.compulyx.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String customerId);


    Optional<Customer> findByCustomerIdAndPin(String customerId, String pin);

}
