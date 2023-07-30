package dev.christopherbell.www.personalsite.models.wfl;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private String city;
    private String state;
    private String streetName;
    private String postalCode;
}
