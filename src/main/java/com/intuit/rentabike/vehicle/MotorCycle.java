package com.intuit.rentabike.vehicle;

import com.intuit.rentabike.enums.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class MotorCycle extends Vehicle{
    private VehicleType vehicleType;

    @Override
    public boolean reservedVehicle() {
        return false;
    }

    @Override
    public boolean returnVehicle() {
        return false;
    }
}
