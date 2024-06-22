package dev.christopherbell.blog.models.wfl;

import dev.christopherbell.blog.models.global.Response;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class WFLResponse extends Response {

  private List<Restaurant> restaurants;
  private Restaurant restaurant;
}