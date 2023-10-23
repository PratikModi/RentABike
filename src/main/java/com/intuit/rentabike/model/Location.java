package com.intuit.rentabike.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Location implements Serializable {
    private int locationId;
    private String latitude;
    private String longitude;
    private String areaCode;

}
