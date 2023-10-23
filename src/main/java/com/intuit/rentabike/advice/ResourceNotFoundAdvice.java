package com.intuit.rentabike.advice;

import com.intuit.rentabike.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ResourceNotFoundAdvice {
    @ExceptionHandler({CustomerNotFoundException.class, ReservationNotFoundException.class, VehicleNotFoundException.class, VehicleCenterNotFoundException.class, VehicleInventoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resourceNotFoundHandler(RuntimeException exception){
        return exception.getMessage();
    }
}
