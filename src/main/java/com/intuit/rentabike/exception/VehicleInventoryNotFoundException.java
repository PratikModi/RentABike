package com.intuit.rentabike.exception;

import java.time.LocalDateTime;

public class VehicleInventoryNotFoundException extends RuntimeException{

    public VehicleInventoryNotFoundException(String model, LocalDateTime date) {
        super("Couldn't find vehicle inventory for vehicle type: "+model+" and date: "+date);
    }
}
