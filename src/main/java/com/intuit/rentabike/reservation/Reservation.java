package com.intuit.rentabike.reservation;

import com.intuit.rentabike.enums.ReservationStatus;

import java.time.LocalDateTime;

public class Reservation {
    private String reservationId;
    private LocalDateTime creationDateTime;
    private ReservationStatus reservationStatus;

}
