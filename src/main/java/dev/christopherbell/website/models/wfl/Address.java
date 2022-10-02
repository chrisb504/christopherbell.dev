package dev.christopherbell.website.models.wfl;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address implements Serializable {
    private String city;
    private String state;
    private String streetName;
    private String postalCode;
}
