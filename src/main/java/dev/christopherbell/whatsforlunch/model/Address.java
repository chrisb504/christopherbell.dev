package dev.christopherbell.whatsforlunch.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {

  private String city;
  private String county;
  private String postalCode;
  private String state;
  private String street1;
  private String street2;
}
