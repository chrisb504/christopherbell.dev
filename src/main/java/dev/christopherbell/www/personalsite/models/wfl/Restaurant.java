package dev.christopherbell.www.personalsite.models.wfl;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {
    private Address address;
    private Integer id;
    private String name;
    private String phoneNumber;
}