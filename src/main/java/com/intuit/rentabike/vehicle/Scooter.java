package com.intuit.rentabike.vehicle;

import com.intuit.rentabike.enums.VehicleType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Scooter extends Vehicle {
    private VehicleType vehicleType;
    public Scooter(){
        super();
    }

    @Override
    public boolean reservedVehicle() {
        return false;
    }

    @Override
    public boolean returnVehicle() {
        return false;
    }
}
