package dev.christopherbell.azurras.models.whatsforlunch;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WFLRestaurant {
    private String address;
    private Integer id;
    private String name;
    private String phoneNumber;
    private String postalCode;

}