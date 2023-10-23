package com.intuit.rentabike.exception;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(int vehicleId) {
        super("Couldn't find vehicle with Id: "+vehicleId);
    }
}
