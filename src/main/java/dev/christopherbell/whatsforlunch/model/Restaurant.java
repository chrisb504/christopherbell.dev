package dev.christopherbell.whatsforlunch.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Restaurant {

  private UUID id;
  private String name;
}