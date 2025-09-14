package dev.christopherbell.whatsforlunch.restaurant;

import com.mongodb.DuplicateKeyException;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.libs.api.exception.ResourceExistsException;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.whatsforlunch.restaurant.model.Restaurant;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link RestaurantService}.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("RestaurantService unit tests")
public class RestaurantServiceTest {
  @Mock private RestaurantMapper restaurantMapper;
  @Mock private RestaurantRepository restaurantRepository;
  @InjectMocks private RestaurantService restaurantService;

  @Test
  @DisplayName("Maps request -> entity, saves, maps to detail, returns detail")
  public void testCreateRestaurant_whenValidRequest_ReturnsRestaurantDetail() throws Exception {
    var request = RestaurantStub.getCreateRestaurantRequestStub();
    Restaurant mapped = null;
    var saved = RestaurantStub.getRestaurantStub(RestaurantStub.ID);
    var detail = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID);

    when(restaurantMapper.toRestaurant(eq(request))).thenReturn(mapped);
    when(restaurantRepository.save(eq(mapped))).thenReturn(saved);
    when(restaurantMapper.toRestaurantDetail(eq(saved))).thenReturn(detail);

    var result = restaurantService.createRestaurant(request);

    assertSame(detail, result, "Expected the mapped detail to be returned");
    verify(restaurantMapper).toRestaurant(eq(request));
    verify(restaurantRepository).save(eq(mapped));
    verify(restaurantMapper).toRestaurantDetail(eq(saved));
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Translates DuplicateKeyException into ResourceExistsException")
  public void testCreateRestaurant_whenDuplicateKey_ThrowsResourceExistsException() {
    var request = RestaurantStub.getCreateRestaurantRequestStub();
    Restaurant mapped = null;

    when(restaurantMapper.toRestaurant(eq(request))).thenReturn(mapped);
    when(restaurantRepository.save(eq(mapped))).thenThrow(DuplicateKeyException.class);

    var ex = assertThrows(ResourceExistsException.class, () -> restaurantService.createRestaurant(request));
    assertTrue(ex.getMessage().contains("already exists"));

    verify(restaurantMapper).toRestaurant(eq(request));
    verify(restaurantRepository).save(eq(mapped));
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Wraps DataAccessException into RuntimeException with message")
  public void testCreateRestaurant_whenDataAccessFails_ThrowsRuntimeException() {
    var request = RestaurantStub.getCreateRestaurantRequestStub();
    Restaurant mapped = null;

    when(restaurantMapper.toRestaurant(eq(request))).thenReturn(mapped);
    when(restaurantRepository.save(eq(mapped))).thenThrow(new DataAccessException("boom") {});

    var ex = assertThrows(RuntimeException.class, () -> restaurantService.createRestaurant(request));
    assertTrue(ex.getMessage().contains("Failed to save restaurant"));

    verify(restaurantMapper).toRestaurant(eq(request));
    verify(restaurantRepository).save(eq(mapped));
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Returns mapped list when repository returns restaurants")
  public void testGetRestaurants_whenSomeExist_ReturnsMappedList() {
    var restaurant1 = RestaurantStub.getRestaurantStub(RestaurantStub.ID);
    var restaurant2 = RestaurantStub.getRestaurantStub(RestaurantStub.ID_2);
    var restaurantDetail1 = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID);
    var restaurantDetail2 = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID_2);

    when(restaurantRepository.findAll()).thenReturn(List.of(restaurant1, restaurant2));
    when(restaurantMapper.toRestaurantDetail(eq(restaurant1))).thenReturn(restaurantDetail1);
    when(restaurantMapper.toRestaurantDetail(eq(restaurant2))).thenReturn(restaurantDetail2);

    var result = restaurantService.getRestaurants();

    assertEquals(2, result.size());
    assertTrue(result.containsAll(List.of(restaurantDetail1, restaurantDetail2)));

    verify(restaurantRepository).findAll();
    verify(restaurantMapper).toRestaurantDetail(eq(restaurant1));
    verify(restaurantMapper).toRestaurantDetail(eq(restaurant2));
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Returns empty list when repository returns empty list")
  public void testGetRestaurants_whenNoneExist_ReturnsEmptyList() {
    when(restaurantRepository.findAll()).thenReturn(List.of());

    var result = restaurantService.getRestaurants();

    assertNotNull(result);
    assertTrue(result.isEmpty());

    verify(restaurantRepository).findAll();
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Throws InvalidRequestException when id is null")
  public void testGetRestaurantById_whenIdNull_ThrowsInvalidRequestException() {
    assertThrows(InvalidRequestException.class, () -> restaurantService.getRestaurantById(null));
    verifyNoInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Throws InvalidRequestException when id is blank")
  public void testGetRestaurantById_whenIdBlank_ThrowsInvalidRequestException() {
    assertThrows(InvalidRequestException.class, () -> restaurantService.getRestaurantById("   "));
    verifyNoInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Returns mapped detail when entity is found")
  public void testGetRestaurantById_whenFound_ReturnsRestaurantDetail() throws Exception {
    var restaurant = RestaurantStub.getRestaurantStub(RestaurantStub.ID);
    var restaurantDetail = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID);


    when(restaurantRepository.findById(eq(RestaurantStub.ID))).thenReturn(Optional.of(restaurant));
    when(restaurantMapper.toRestaurantDetail(eq(restaurant))).thenReturn(restaurantDetail);

    var result = restaurantService.getRestaurantById(RestaurantStub.ID);

    assertSame(restaurantDetail, result);

    verify(restaurantRepository).findById(eq(RestaurantStub.ID));
    verify(restaurantMapper).toRestaurantDetail(eq(restaurant));
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }

  @Test
  @DisplayName("Throws ResourceNotFoundException when entity is not found")
  public void testGetRestaurantById_whenNotFound_ThrowsResourceNotFoundException() {

    when(restaurantRepository.findById(eq(RestaurantStub.ID))).thenReturn(Optional.empty());

    var ex = assertThrows(ResourceNotFoundException.class, () -> restaurantService.getRestaurantById(RestaurantStub.ID));
    assertTrue(ex.getMessage().contains(RestaurantStub.ID));

    verify(restaurantRepository).findById(eq(RestaurantStub.ID));
    verifyNoMoreInteractions(restaurantMapper, restaurantRepository);
  }
}
