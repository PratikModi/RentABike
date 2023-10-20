package com.intuit.rentabike.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private Address address;
}
