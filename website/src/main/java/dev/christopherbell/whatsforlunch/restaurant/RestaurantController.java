package dev.christopherbell.whatsforlunch.restaurant;

import dev.christopherbell.libs.api.APIVersion;
import dev.christopherbell.libs.api.model.Response;
import dev.christopherbell.permission.PermissionService;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantCreateRequest;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantDetail;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantUpdateRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for restaurant management under {@code /api/whatsforlunch/restaurant}.
 */
@RequiredArgsConstructor
@RequestMapping("/api/whatsforlunch/restaurant")
@RestController
public class RestaurantController {
  private final PermissionService permissionService;
  private final RestaurantService restaurantService;

  /**
   * Creates a new restaurant.
   *
   * @param request create request payload
   * @return HTTP 201 with the created restaurant
   */
  @PostMapping(
      value = APIVersion.V20250912,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<RestaurantDetail>> createRestaurant(
      @RequestBody RestaurantCreateRequest request
  ) throws Exception {
    var response = restaurantService.createRestaurant(request);
    return new ResponseEntity<>(
        Response.<RestaurantDetail>builder()
            .payload(response)
            .success(true)
            .build(), HttpStatus.CREATED);
  }

  /**
   * Deletes an existing restaurant.
   *
   * @param id the restaurant ID to delete
   * @return HTTP 200 with the deleted restaurant
   */
  @DeleteMapping(
      value = APIVersion.V20250913 + "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<RestaurantDetail>> deleteRestaurantById(
      @PathVariable String id
  ) throws Exception {
    var response = restaurantService.deleteRestaurantById(id);
    return new ResponseEntity<>(
        Response.<RestaurantDetail>builder()
            .payload(response)
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Retrieves a restaurant by ID.
   *
   * @param id the restaurant ID
   * @return HTTP 200 with the matching restaurant
   */
  @GetMapping(value = APIVersion.V20250912 + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<RestaurantDetail>> getRestaurantById(
      @PathVariable String id
  ) throws Exception {
    var response = restaurantService.getRestaurantById(id);
    return new ResponseEntity<>(
        Response.<RestaurantDetail>builder()
            .payload(response)
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Lists all restaurants.
   *
   * @return HTTP 200 with all restaurants
   */
  @GetMapping(value = APIVersion.V20250912, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<List<RestaurantDetail>>> getRestaurants() throws Exception {
    var response = restaurantService.getRestaurants();
    return new ResponseEntity<>(
        Response.<List<RestaurantDetail>>builder()
            .payload(response)
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Updates an existing restaurant.
   *
   * @return HTTP 202 with the updated restaurant
   */
  @PutMapping(
      value = APIVersion.V20250913,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<RestaurantDetail>> updateRestaurantById(
      @RequestBody RestaurantUpdateRequest request
  ) throws Exception {
    var response = restaurantService.updateRestaurant(request);
    return new ResponseEntity<>(
        Response.<RestaurantDetail>builder()
            .payload(response)
            .success(true)
            .build(), HttpStatus.ACCEPTED);
  }
}
