package com.intuit.rentabike.dao;

import com.intuit.rentabike.exception.VehicleCenterNotFoundException;
import com.intuit.rentabike.model.VehicleCenter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class VehicleCenterRepository {
    private final Map<String, VehicleCenter> vehicleCenters;
    private final AtomicInteger idGenerator;

    public VehicleCenterRepository() {
        this.vehicleCenters = new HashMap<>();
        this.idGenerator = new AtomicInteger(1);
    }

    public VehicleCenter save(VehicleCenter vehicleCenter){
        String vehicleCenterId = String.valueOf(idGenerator.getAndIncrement());
        vehicleCenter.setVehicleCenterId(vehicleCenterId);
        vehicleCenters.put(vehicleCenterId,vehicleCenter);
        return vehicleCenter;
    }

    public void update(VehicleCenter vehicleCenter){
        if(!vehicleCenters.containsKey(vehicleCenter.getVehicleCenterId())){
            throw new VehicleCenterNotFoundException(vehicleCenter.getVehicleCenterId());
        }
        vehicleCenters.put(vehicleCenter.getVehicleCenterId(),vehicleCenter);
    }

    public void deleteById(String vehicleCenterId){
        if(!vehicleCenters.containsKey(vehicleCenterId) || !vehicleCenters.get(vehicleCenterId).isActive()){
            throw new VehicleCenterNotFoundException(vehicleCenterId);
        }
        var vehicleCenter = vehicleCenters.get(vehicleCenterId);
        vehicleCenter.setActive(Boolean.FALSE);
    }

    public VehicleCenter getById(String vehicleCenterId){
        if(!vehicleCenters.containsKey(vehicleCenterId)){
            throw new VehicleCenterNotFoundException(vehicleCenterId);
        }
        return vehicleCenters.get(vehicleCenterId);
    }
}
