package com.intuit.rentabike.exception;

public class VehicleCenterNotFoundException extends RuntimeException{

    public VehicleCenterNotFoundException(String vehicleCenterId) {
        super("Couldn't find Vehicle Center with Id: "+vehicleCenterId);
    }
}
