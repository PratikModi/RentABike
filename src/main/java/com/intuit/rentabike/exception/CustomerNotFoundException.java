package com.intuit.rentabike.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(int customerId) {
        super("Couldn't find customer with Id: "+customerId);
    }
}
