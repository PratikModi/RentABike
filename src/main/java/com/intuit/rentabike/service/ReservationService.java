package com.intuit.rentabike.service;

import com.intuit.rentabike.reservation.Reservation;

public interface ReservationService {
    Reservation reserveBike(Reservation reservation);
    void returnBike(String reservationId);
    void cancelReservation(String reservationId);
    Reservation getReservationById(String reservationId);
}
