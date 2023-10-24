package com.intuit.rentabike.exception;

import com.intuit.rentabike.reservation.Reservation;

public class ReservationNotAvailableException extends RuntimeException{

    public ReservationNotAvailableException(Reservation reservation) {
        super("Reservation not available for below criteria:\n"+reservation.toString());
    }
}
