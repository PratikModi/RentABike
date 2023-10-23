package com.intuit.rentabike.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
@Data
@Builder
@AllArgsConstructor
public class VehicleInventory implements Serializable {
    @ToString.Exclude
    @JsonIgnore
    private AtomicInteger idGenerator = new AtomicInteger(1);
    private int inventoryId;
    private String make;
    private String model;
    private LocalDateTime date;
    private int totalVehicle;
    private int totalReserved;
    private String vehicleCenterId;
    private BigDecimal price;

    public VehicleInventory(String model, String make, LocalDateTime date, int totalVehicle, int totalReserved, BigDecimal price, String vehicleCenterId) {
        this.inventoryId = idGenerator.getAndIncrement();
        this.make = make;
        this.model = model;
        this.date = date;
        this.totalVehicle = totalVehicle;
        this.totalReserved = totalReserved;
        this.price = price;
        this.vehicleCenterId = vehicleCenterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleInventory that = (VehicleInventory) o;
        return Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(date, that.date) && Objects.equals(vehicleCenterId, that.vehicleCenterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, date, vehicleCenterId);
    }
}
