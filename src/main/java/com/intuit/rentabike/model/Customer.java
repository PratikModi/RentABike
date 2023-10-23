package com.intuit.rentabike.model;

import com.intuit.rentabike.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer{
    private int customerId;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    private Address address;
    private boolean isActive;
    private String drivingLicenseNumber;
    private LocalDate drivingLicenseExpiryDate;

    public Customer(String name, String email, String phone) {
        if(!isValidEmail(email)){
            throw new InvalidInputException("Invalid Email Address: "+email);
        }
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isActive = Boolean.TRUE;
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^(.+)@(\\S+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
