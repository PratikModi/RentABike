package com.intuit.rentabike.model;

import com.intuit.rentabike.enums.VehicleStatus;
import com.intuit.rentabike.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Bike implements Serializable {
    private int bikeId;
    @NotNull
    private String licenseNumber;
    @NotNull
    private String vehicleCenterId;
    @NotNull
    private VehicleStatus vehicleStatus;
    @NotNull
    private String model;
    @NotNull
    private String make;
    private boolean isActive;
    @NotNull
    private VehicleType vehicleType;
    @NotNull
    private Double price;

    public Bike(String licenseNumber, String vehicleCenterId, String model, String make, VehicleType vehicleType, Double price) {
        this.licenseNumber = licenseNumber;
        this.vehicleCenterId = vehicleCenterId;
        this.model = model;
        this.make = make;
        this.vehicleType = vehicleType;
        this.price = price;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
        this.isActive=Boolean.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(licenseNumber, bike.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }
}
