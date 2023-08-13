package com.compulyx.backend.security;

import com.compulyx.backend.entities.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final Customer customer;

    public UserPrincipal(Customer customer) {
        this.customer = customer;
    }

    // Factory method to create a UserPrincipal object
    public static UserPrincipal create(Customer customer) {
        return new UserPrincipal(customer);
    }

    @Override
    public String getUsername() {
        return customer.getCustomerId();
    }

    @Override
    public String getPassword() {
        return customer.getPin();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can customize authorities based on roles or permissions if needed
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

