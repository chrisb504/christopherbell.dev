package dev.christopherbell.whatsforlunch.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Restaurant {

  private Integer id;
  private String name;
}