package com.intuit.rentabike.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private int addressId;
    private String streetName;
    private String city;
    private String areaCode;
    private String country;
}
