package com.intuit.rentabike.service;

import com.intuit.rentabike.enums.VehicleType;
import com.intuit.rentabike.model.response.VehicleResponse;

import java.util.List;

public interface SearchService {
    List<VehicleResponse> getVehiclesByType(VehicleType vehicleType);
    List<VehicleResponse> getVehiclesByModel(String model);
    List<VehicleResponse> getVehiclesByMake(String make);
}
