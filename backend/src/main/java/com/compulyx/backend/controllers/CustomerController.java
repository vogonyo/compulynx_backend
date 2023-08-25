package com.compulyx.backend.controllers;


import com.compulyx.backend.entities.Account;
import com.compulyx.backend.entities.Customer;
import com.compulyx.backend.repositories.AccountRepository;
import com.compulyx.backend.repositories.CustomerRepository;
import com.compulyx.backend.security.JwtTokenProvider;
import com.compulyx.backend.vm.CustomerLoginRequest;
import com.compulyx.backend.vm.JwtToken;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@Api(tags = "Customer Management")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> signup(@RequestBody Customer customer) {
        // Check if the customer with the given customerId already exists
        Optional<Customer> existingCustomer = customerRepository.findByCustomerId(customer.getCustomerId());
        if (existingCustomer.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Generate a 4-digit PIN
        int pin = new Random().nextInt(9000) + 1000;
        customer.setPin(String.valueOf(pin));

        Customer registeredCustomer = customerRepository.save(customer);

        // Create an associated account for the customer
        Account account = new Account();
        account.setCustomer(registeredCustomer);
        account.setAccountId(customer.getCustomerId());
        account.setBalance(0.0);

        accountRepository.save(account);
        return ResponseEntity.ok(registeredCustomer);

    }



    @PostMapping("/login")
    public JwtToken login(@RequestBody CustomerLoginRequest customerLoginRequest) {
        log.info("Login request received for customer ID: {}", customerLoginRequest.getCustomerId());

        Optional<Customer> customer = customerRepository.findByCustomerIdAndPin(
                customerLoginRequest.getCustomerId(), customerLoginRequest.getPin());

        if (customer.isPresent()) {
            String customerId = customer.get().getCustomerId();
            log.info("Login successful for customer ID: {}", customerId);

            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(jwtTokenProvider.generateToken(customerId));
            return jwtToken;
        } else {
            log.info("Login failed for customer ID: {}", customerLoginRequest.getCustomerId());
            throw new RuntimeException("Customer does not exist or incorrect PIN"); // Adjust the exception type as needed
        }
    }

}
