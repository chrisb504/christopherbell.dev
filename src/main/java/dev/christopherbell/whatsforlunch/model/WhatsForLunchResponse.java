package dev.christopherbell.whatsforlunch.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WhatsForLunchResponse {

  private List<Restaurant> restaurants;
}