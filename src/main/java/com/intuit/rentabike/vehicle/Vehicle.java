package com.intuit.rentabike.vehicle;

import com.intuit.rentabike.enums.VehicleStatus;
import lombok.Builder;
import lombok.Data;

public abstract class Vehicle {
    private String licenseNumber;
    private String stockNumber;
    private VehicleStatus vehicleStatus;
    private String model;
    private String make;

    public Vehicle(){
    }

    public abstract boolean reservedVehicle();
    public abstract boolean returnVehicle();
}
