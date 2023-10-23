package com.intuit.rentabike.model.response;

import com.intuit.rentabike.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VehicleResponse {
    private VehicleType vehicleType;
    private List<String> vehicleCenterIds;
    private String make;
    private String model;
    private Double price;

    public VehicleResponse(VehicleType vehicleType, List<String> vehicleCenterIds, String make, String model, double price) {
        this.vehicleType = vehicleType;
        this.vehicleCenterIds = vehicleCenterIds;
        this.make = make;
        this.model = model;
        this.price = price;
    }

}
