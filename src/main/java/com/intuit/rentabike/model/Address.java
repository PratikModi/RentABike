package com.intuit.rentabike.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private int addressId;
    @NotNull
    private String streetName;
    @NotNull
    private String city;
    @NotNull
    private String areaCode;
    @NotNull
    private String country;

    public Address(String streetName, String city, String areaCode, String country) {
        this.streetName = streetName;
        this.city = city;
        this.areaCode = areaCode;
        this.country = country;
    }
}
