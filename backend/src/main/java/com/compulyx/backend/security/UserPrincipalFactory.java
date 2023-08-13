package com.compulyx.backend.security;

import com.compulyx.backend.entities.Customer;

public class UserPrincipalFactory {

    private UserPrincipalFactory() {
        // Private constructor to prevent instantiation
    }

    public static UserPrincipal create(Customer customer) {
        return new UserPrincipal(customer);
    }
}
