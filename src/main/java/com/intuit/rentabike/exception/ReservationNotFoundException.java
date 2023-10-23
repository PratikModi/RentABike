package com.intuit.rentabike.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(String reservationId){
        super("Couldn't find reservation with Id: "+reservationId);
    }
}
