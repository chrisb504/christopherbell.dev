package dev.christopherbell.blog.controllers;

import dev.christopherbell.blog.models.global.Response;
import dev.christopherbell.blog.services.WFLService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WFLController {

  private final WFLService wflService;


  /**
   * Takes an id and uses it to get a matching restaurant.
   *
   * @return WFLResponse
   */
  @GetMapping(value = "/api/wfl/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getRestaurantById(HttpServletRequest request, @PathVariable String id) {
    var response = this.wflService.getRestaurantById(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Returns all restaurants in the database.
   *
   * @return WFLResponse
   */
  @GetMapping(value = "/api/wfl/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getRestaurants(HttpServletRequest request) {
    var response = this.wflService.getRestaurants();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}