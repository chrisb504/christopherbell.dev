package dev.christopherbell.thevoid.account.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {

  public String city;
  public String state;
  public String country;
}
