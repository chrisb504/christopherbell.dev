package dev.christopherbell.whatsforlunch.restaurant;

import dev.christopherbell.whatsforlunch.restaurant.model.CreateRestaurantRequest;
import dev.christopherbell.whatsforlunch.restaurant.model.Restaurant;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct-based mapper for converting between domain entities
 * ({@link Restaurant}) and data transfer objects (DTOs).
 *
 * <p>This mapper abstracts the conversion logic used in service
 * and controller layers, ensuring a clean separation between
 * persistence models and API-facing contracts.</p>
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper {

  /**
   * Converts a {@link Restaurant} entity to a {@link RestaurantDetail} DTO.
   * <p>
   * This is typically used when returning data to clients via API responses.
   * </p>
   *
   * @param restaurant the restaurant entity to convert (must not be {@code null})
   * @return a new {@link RestaurantDetail} representing the provided entity,
   *         or {@code null} if the input is {@code null}
   */
  RestaurantDetail toRestaurantDetail(Restaurant restaurant);

  /**
   * Converts a {@link RestaurantDetail} DTO back to a {@link Restaurant} entity.
   * <p>
   * This method is useful when persisting data that was previously retrieved
   * as a DTO. Audit fields are expected to be managed separately by the service
   * or persistence layer.
   * </p>
   *
   * @param restaurantDetail the DTO to convert (must not be {@code null})
   * @return a new {@link Restaurant} entity corresponding to the provided DTO,
   *         or {@code null} if the input is {@code null}
   */
  Restaurant toRestaurant(RestaurantDetail restaurantDetail);

  /**
   * Converts a {@link CreateRestaurantRequest} DTO to a new {@link Restaurant} entity.
   * <p>
   * This mapping intentionally ignores system-managed fields such as {@code id},
   * {@code createdBy}, {@code createdOn}, {@code lastModifiedBy}, and
   * {@code lastUpdatedOn}, as these are populated by the persistence and
   * auditing infrastructure.
   * </p>
   *
   * @param request the creation request containing restaurant details (must not be {@code null})
   * @return a new {@link Restaurant} entity populated from the request data,
   *         with audit fields left unset
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdOn", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastUpdatedOn", ignore = true)
  Restaurant toRestaurant(CreateRestaurantRequest request);
}
