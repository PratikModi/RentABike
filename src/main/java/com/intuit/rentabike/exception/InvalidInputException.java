package com.intuit.rentabike.exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String input){
        super("Invalid Input :\n"+input);
    }
}
