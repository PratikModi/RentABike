package com.intuit.rentabike.service;

import com.intuit.rentabike.model.VehicleCenter;

public interface VehicleCenterService {
    VehicleCenter addVehicleCenter(VehicleCenter vehicleCenter);
    void updateVehicleCenter(VehicleCenter vehicleCenter);
    void deleteVehicleCenterById(String vehicleCenterId);
    VehicleCenter getVehicleCenterById(String vehicleCenterId);
}
