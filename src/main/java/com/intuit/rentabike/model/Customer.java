package com.intuit.rentabike.model;

import com.intuit.rentabike.enums.ReservationStatus;
import com.intuit.rentabike.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Customer extends User{
    private String drivingLicenseNumber;
    private LocalDate drivingLicenseExpiryDate;

    public Customer(String drivingLicenseNumber, LocalDate drivingLicenseExpiryDate) {
        super();
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
    }

    public List<Reservation> getReservation(){
        return null;
    }
}
