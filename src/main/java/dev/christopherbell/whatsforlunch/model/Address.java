package dev.christopherbell.whatsforlunch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Address {

  private String city;
  private String county;
  private String postalCode;
  private String state;
  private String street1;
  private String street2;
}
