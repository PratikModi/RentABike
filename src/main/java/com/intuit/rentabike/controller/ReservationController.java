package com.intuit.rentabike.controller;

import com.intuit.rentabike.reservation.Reservation;
import com.intuit.rentabike.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;

@RestController
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/v1/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody @NotNull Reservation reservation){
        Reservation postReservation = reservationService.reserveBike(reservation);
        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postReservation.getReservationId())
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,location).body(postReservation);
    }

    @GetMapping(value = "/v1/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable @NotNull String id){
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping(value = "/v1/reservations/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable @NotNull String id){
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/v1/reservations/{id}/complete")
    public ResponseEntity<Void> completeReservation(@PathVariable @NotNull String id){
        reservationService.returnBike(id);
        return ResponseEntity.noContent().build();
    }


}
