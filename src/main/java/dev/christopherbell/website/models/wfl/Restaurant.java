package dev.christopherbell.website.models.wfl;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Restaurant implements Serializable {
    private Address address;
    private Integer id;
    private String name;
    private String phoneNumber;
}