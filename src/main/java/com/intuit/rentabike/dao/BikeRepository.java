package com.intuit.rentabike.dao;

import com.intuit.rentabike.enums.VehicleStatus;
import com.intuit.rentabike.exception.VehicleNotFoundException;
import com.intuit.rentabike.model.Bike;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Qualifier("MotorCycle")
public class BikeRepository{

    private final Map<Integer, Bike> bikes;
    private final AtomicInteger idGenerator;

    public BikeRepository() {
        this.bikes = new HashMap<>();
        this.idGenerator = new AtomicInteger(1);
    }

    public Bike save(Bike bike) {
        int vehicleId = idGenerator.getAndIncrement();
        bike.setBikeId(vehicleId);
        bikes.put(vehicleId,bike);
        return bike;
    }

    public void updateBike(Bike bike) {
        if(!bikes.containsKey(bike.getBikeId())){
            throw new VehicleNotFoundException(bike.getBikeId());
        }
        bikes.put(bike.getBikeId(), bike);
    }

    public void deleteBikeById(int bikeId) {
        if(!bikes.containsKey(bikeId)){
            throw new VehicleNotFoundException(bikeId);
        }
        Bike bike = bikes.get(bikeId);
        bike.setActive(Boolean.FALSE);
    }

    public List<Bike> getAllBikes() {
        return bikes.values().stream().toList();
    }

    public Bike getBikeById(int vehicleId) {
        if(!bikes.containsKey(vehicleId) || StringUtils.equalsAnyIgnoreCase(bikes.get(vehicleId).getVehicleStatus().name(),VehicleStatus.OTHER.name(),VehicleStatus.BEING_SERVICED.name()) || !bikes.get(vehicleId).isActive()){
            throw new VehicleNotFoundException(vehicleId);
        }
        return bikes.get(vehicleId);
    }
}
