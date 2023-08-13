package com.compulyx.backend.security;

import com.compulyx.backend.entities.Customer;
import com.compulyx.backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String customerId) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with customerId: " + customerId));

        return UserPrincipalFactory.create(customer); // Use your factory method here
    }
}

