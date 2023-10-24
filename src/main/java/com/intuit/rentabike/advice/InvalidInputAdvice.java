package com.intuit.rentabike.advice;


import com.intuit.rentabike.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
@ResponseBody
public class InvalidInputAdvice<T> {
    @ExceptionHandler({InvalidInputException.class,ReservationNotAvailableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resourceNotFoundHandler(RuntimeException exception){
        return exception.getMessage();
    }
}
