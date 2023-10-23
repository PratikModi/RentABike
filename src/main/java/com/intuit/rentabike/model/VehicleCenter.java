package com.intuit.rentabike.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class VehicleCenter {
    private String vehicleCenterId;
    @NotNull
    private String name;
    @NotNull
    private Address address;
    @NotNull
    private int noOfSlots;
    private List<Slot> slots;
    private boolean isActive;

    public VehicleCenter(String name, Address address, int noOfSlots) {
        this.name = name;
        this.address = address;
        this.noOfSlots = noOfSlots;
        this.isActive = Boolean.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleCenter that = (VehicleCenter) o;
        return Objects.equals(vehicleCenterId, that.vehicleCenterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleCenterId);
    }
}


