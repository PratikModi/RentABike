package com.intuit.rentabike.dao;

import com.intuit.rentabike.enums.ReservationStatus;
import com.intuit.rentabike.exception.ReservationNotFoundException;
import com.intuit.rentabike.reservation.Reservation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReservationRepository {
    Map<String, Reservation> reservations;

    public ReservationRepository() {
        this.reservations = new HashMap<>();
    }

    public Reservation save(Reservation reservation){
        reservation.setReservationId(UUID.randomUUID().toString());
        reservations.put(reservation.getReservationId(),reservation);
        return reservation;
    }

    public Reservation getReservationById(String reservationId){
        if(!reservations.containsKey(reservationId)){
            throw new ReservationNotFoundException(reservationId);
        }
        return reservations.get(reservationId);
    }

    public void updateReservationStatus(String reservationId,ReservationStatus reservationStatus){
        if(!reservations.containsKey(reservationId)){
            throw new ReservationNotFoundException(reservationId);
        }
        Reservation reservation = reservations.get(reservationId);
        reservation.setReservationStatus(reservationStatus);
    }

}
