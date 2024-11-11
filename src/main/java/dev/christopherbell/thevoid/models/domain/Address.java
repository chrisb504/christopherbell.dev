package dev.christopherbell.thevoid.models.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {

  public String city;
  public String state;
  public String country;
}
