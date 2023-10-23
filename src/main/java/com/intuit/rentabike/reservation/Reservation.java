package com.intuit.rentabike.reservation;

import com.intuit.rentabike.enums.ReservationStatus;
import com.intuit.rentabike.validator.Validator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Validator, Serializable {
    private String reservationId;
    private LocalDateTime creationDateTime;
    private ReservationStatus reservationStatus;
    private int bikeId;
    @NotNull
    private int customerId;
    @NotNull
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDataTime;
    @NotNull
    private String pickupVehicleCenterId;
    @NotNull
    private String returnVehicleCenterId;
    @NotNull
    private String model;
    @NotNull
    private String make;

    public Reservation(ReservationStatus reservationStatus, int bikeId, int customerId, LocalDateTime startDateTime, LocalDateTime endDataTime, String pickupVehicleCenterId, String returnVehicleCenterId, String model, String make) {
        this.creationDateTime = LocalDateTime.now();
        this.reservationStatus = reservationStatus;
        this.bikeId = bikeId;
        this.customerId = customerId;
        this.startDateTime = startDateTime;
        this.endDataTime = endDataTime;
        this.pickupVehicleCenterId = pickupVehicleCenterId;
        this.returnVehicleCenterId = returnVehicleCenterId;
        this.model = model;
        this.make = make;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }

    @Override
    public boolean validate() {
        if(this.startDateTime.isBefore(LocalDateTime.now()) || this.endDataTime.isBefore(this.startDateTime) || this.endDataTime.isBefore(LocalDateTime.now())) {
            return false;
        }else return !this.startDateTime.isAfter(LocalDateTime.now().plusDays(30)) && !this.endDataTime.isAfter(LocalDateTime.now().plusDays(30));
    }
}
