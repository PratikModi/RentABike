package com.intuit.rentabike.model;

import lombok.Data;

@Data
public class Location {
    private int locationId;
    private String latitude;
    private String longitude;
    private String areaCode;

}
