package com.maritvandijk.payroll;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Could not find customer " + customerId);
    }
}
