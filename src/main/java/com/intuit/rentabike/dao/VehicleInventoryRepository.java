package com.intuit.rentabike.dao;

import com.intuit.rentabike.exception.VehicleInventoryNotFoundException;
import com.intuit.rentabike.reservation.VehicleInventory;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class VehicleInventoryRepository {

    private final Map<VehicleInventoryIndex, VehicleInventory> vehicleInventoryMap;

    public VehicleInventoryRepository() {
        this.vehicleInventoryMap = new HashMap<>();
    }
    public VehicleInventory save(VehicleInventory vehicleInventory){
        VehicleInventoryIndex index = new VehicleInventoryIndex(vehicleInventory.getModel(),vehicleInventory.getDate().toLocalDate());
        if(vehicleInventoryMap.containsKey(index)){
            var entry = vehicleInventoryMap.get(index);
            vehicleInventory.setTotalReserved(vehicleInventory.getTotalReserved()+ entry.getTotalReserved());
            vehicleInventory.setTotalVehicle(vehicleInventory.getTotalVehicle()+ entry.getTotalVehicle());
            vehicleInventory.setInventoryId(entry.getInventoryId());
        }
        vehicleInventoryMap.put(index,vehicleInventory);
        return vehicleInventory;
    }

    public void update(VehicleInventory vehicleInventory){
        VehicleInventoryIndex index = new VehicleInventoryIndex(vehicleInventory.getModel(),vehicleInventory.getDate().toLocalDate());
        if(!vehicleInventoryMap.containsKey(index)){
            throw new VehicleInventoryNotFoundException(vehicleInventory.getModel(),vehicleInventory.getDate());
        }
        vehicleInventoryMap.put(index,vehicleInventory);
    }
    public VehicleInventory get(VehicleInventoryIndex vehicleInventoryIndex){
        return vehicleInventoryMap.get(vehicleInventoryIndex);
    }



    public boolean isBikeAvailable(VehicleInventoryIndex index){
        if(!vehicleInventoryMap.containsKey(index)){
            return true;
        }
        return vehicleInventoryMap.get(index).getTotalReserved()<vehicleInventoryMap.get(index).getTotalVehicle();
    }

    public List<VehicleInventory> getAllInventory(){
        return vehicleInventoryMap.values().stream().toList();
    }

    @Data
    @Builder
    public static class VehicleInventoryIndex{
        private String model;
        private LocalDate date;

        public VehicleInventoryIndex(String model, LocalDate date) {
            this.model = model;
            this.date = date;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VehicleInventoryIndex that = (VehicleInventoryIndex) o;
            return Objects.equals(model, that.model) && Objects.equals(date, that.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(model, date);
        }
    }

}

