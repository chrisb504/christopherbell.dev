package dev.christopherbell.whatsforlunch.restaurant;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.christopherbell.libs.api.APIVersion;
import dev.christopherbell.libs.api.controller.ControllerExceptionHandler;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.permission.PermissionService;
import dev.christopherbell.libs.test.TestUtil;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantCreateRequest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RestaurantController.class)
@Import(ControllerExceptionHandler.class)
public class RestaurantControllerTest {
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockitoBean private PermissionService permissionService;
  @MockitoBean private RestaurantService restaurantService;

  @Test
  @DisplayName("Should create a restaurant when caller has ADMIN role.")
  @WithMockUser(authorities = {"ADMIN"})
  public void testCreateRestaurant() throws Exception {
    var request = TestUtil.readJsonAsString("/request/create-restaurant-request.json");
    var requestObject =
        TestUtil.readJsonAsObject(
            "/request/create-restaurant-request.json", RestaurantCreateRequest.class);
    var response = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID);

    when(restaurantService.createRestaurant(eq(requestObject))).thenReturn(response);

    mockMvc
        .perform(
            post("/api/whatsforlunch/restaurant" + APIVersion.V20250912)
                .with(csrf())
                .content(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.payload").isNotEmpty())
        .andExpect(jsonPath("$.payload.id").value(RestaurantStub.ID))
        .andExpect(jsonPath("$.payload.name").value(RestaurantStub.NAME))
        .andExpect(jsonPath("$.payload.address.street1").value(RestaurantStub.STREET_1))
        .andExpect(jsonPath("$.payload.address.city").value(RestaurantStub.CITY))
        .andExpect(jsonPath("$.payload.address.state").value(RestaurantStub.STATE))
        .andExpect(jsonPath("$.payload.address.country").value(RestaurantStub.COUNTRY))
        .andExpect(jsonPath("$.payload.address.postalCode").value(RestaurantStub.POSTAL_CODE))
        .andExpect(jsonPath("$.payload.phoneNumber").value(RestaurantStub.PHONE_NUMBER))
        .andExpect(jsonPath("$.payload.website").value(RestaurantStub.WEBSITE));

    verify(restaurantService).createRestaurant(eq(requestObject));
  }

  @Test
  @DisplayName("Should return 400 Bad Request when InvalidRequestException is thrown.")
  @WithMockUser(authorities = {"ADMIN"})
  public void testCreateRestaurant_whenInvalidRequestExceptionIsThrown() throws Exception {
    var request = TestUtil.readJsonAsString("/request/create-restaurant-request.json");
    var requestObject =
        TestUtil.readJsonAsObject(
            "/request/create-restaurant-request.json", RestaurantCreateRequest.class);
    when(restaurantService.createRestaurant(eq(requestObject)))
        .thenThrow(new InvalidRequestException("Bad Request"));

    mockMvc
        .perform(
            post("/api/whatsforlunch/restaurant" + APIVersion.V20250912)
                .with(csrf())
                .content(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest());

    verify(restaurantService).createRestaurant(eq(requestObject));
  }

  @Test
  @DisplayName("Should return 401 Forbidden when user has no permissions.")
  public void testCreateRestaurant_whenUserHasNoPermissions() throws Exception {
    mockMvc
        .perform(
            post("/api/whatsforlunch/restaurant" + APIVersion.V20250912)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isUnauthorized());

    verifyNoInteractions(restaurantService);
  }

  @Test
  @DisplayName("Should get restaurant by id when caller has ADMIN role.")
  @WithMockUser(authorities = {"ADMIN"})
  public void testGetAllRestaurantById() throws Exception {
    var restaurantDetail = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID);
    when(restaurantService.getRestaurantById(eq(RestaurantStub.ID)))
        .thenReturn(restaurantDetail);

    mockMvc
        .perform(
            get("/api/whatsforlunch/restaurant" + APIVersion.V20250912 + "/" + RestaurantStub.ID)
                .content(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.payload.id").value(RestaurantStub.ID))
        .andExpect(jsonPath("$.payload.name").value(RestaurantStub.NAME))
        .andExpect(jsonPath("$.payload.address.street1").value(RestaurantStub.STREET_1))
        .andExpect(jsonPath("$.payload.address.city").value(RestaurantStub.CITY))
        .andExpect(jsonPath("$.payload.address.state").value(RestaurantStub.STATE))
        .andExpect(jsonPath("$.payload.address.country").value(RestaurantStub.COUNTRY))
        .andExpect(jsonPath("$.payload.address.postalCode").value(RestaurantStub.POSTAL_CODE))
        .andExpect(jsonPath("$.payload.phoneNumber").value(RestaurantStub.PHONE_NUMBER))
        .andExpect(jsonPath("$.payload.website").value(RestaurantStub.WEBSITE));

    verify(restaurantService).getRestaurantById(eq(RestaurantStub.ID));
  }

  @Test
  @DisplayName("Should throw ResourceNotFoundException when restaurant does not exist.")
  @WithMockUser(authorities = {"ADMIN"})
  public void testGetAllRestaurantById_whenResourceNotFoundExceptionIsThrown() throws Exception {
    when(restaurantService.getRestaurantById(eq(RestaurantStub.ID))).thenThrow(new ResourceNotFoundException());

    mockMvc
        .perform(
            get("/api/whatsforlunch/restaurant" + APIVersion.V20250912 + "/" + RestaurantStub.ID)
                .content(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false));

    verify(restaurantService).getRestaurantById(eq(RestaurantStub.ID));
  }

  @Test
  @DisplayName("Should return 401 Unauthorized when caller has no permissions.")
  public void testGetAllRestaurantById_whenCallerHasNoPermissions() throws Exception {
    mockMvc
        .perform(
            get("/api/whatsforlunch/restaurant" + APIVersion.V20250912 + "/" + RestaurantStub.ID)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());

    verifyNoInteractions(restaurantService);
  }

  @Test
  @DisplayName("Should get all restaurants when caller has ADMIN role.")
  @WithMockUser(authorities = {"ADMIN"})
  public void testGetAllRestaurants() throws Exception {
    var restaurant1 = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID);
    var restaurant2 = RestaurantStub.getRestaurantDetailStub(RestaurantStub.ID_2);
    var restaurantDetails = List.of(restaurant1, restaurant2);
    when(restaurantService.getRestaurants()).thenReturn(restaurantDetails);

    mockMvc
        .perform(
            get("/api/whatsforlunch/restaurant" + APIVersion.V20250912)
                .content(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.payload").isArray())
        .andExpect(jsonPath("$.payload[0].id").value(RestaurantStub.ID))
        .andExpect(jsonPath("$.payload[0].name").value(RestaurantStub.NAME))
        .andExpect(jsonPath("$.payload[0].address.street1").value(RestaurantStub.STREET_1))
        .andExpect(jsonPath("$.payload[0].address.city").value(RestaurantStub.CITY))
        .andExpect(jsonPath("$.payload[0].address.state").value(RestaurantStub.STATE))
        .andExpect(jsonPath("$.payload[0].address.country").value(RestaurantStub.COUNTRY))
        .andExpect(jsonPath("$.payload[0].address.postalCode").value(RestaurantStub.POSTAL_CODE))
        .andExpect(jsonPath("$.payload[0].phoneNumber").value(RestaurantStub.PHONE_NUMBER))
        .andExpect(jsonPath("$.payload[0].website").value(RestaurantStub.WEBSITE))
        .andExpect(jsonPath("$.payload[1].id").value(RestaurantStub.ID_2))
        .andExpect(jsonPath("$.payload[1].name").value(RestaurantStub.NAME))
        .andExpect(jsonPath("$.payload[1].address.street1").value(RestaurantStub.STREET_1))
        .andExpect(jsonPath("$.payload[1].address.city").value(RestaurantStub.CITY))
        .andExpect(jsonPath("$.payload[1].address.state").value(RestaurantStub.STATE))
        .andExpect(jsonPath("$.payload[1].address.country").value(RestaurantStub.COUNTRY))
        .andExpect(jsonPath("$.payload[1].address.postalCode").value(RestaurantStub.POSTAL_CODE))
        .andExpect(jsonPath("$.payload[1].phoneNumber").value(RestaurantStub.PHONE_NUMBER))
        .andExpect(jsonPath("$.payload[1].website").value(RestaurantStub.WEBSITE));

    verify(restaurantService).getRestaurants();
  }

  @Test
  @DisplayName("Should get all restaurants and return a response that is empty.")
  @WithMockUser(authorities = {"ADMIN"})
  public void testGetAllRestaurants_whenResponseIsEmpty() throws Exception {
    when(restaurantService.getRestaurants()).thenReturn(List.of());

    mockMvc
        .perform(
            get("/api/whatsforlunch/restaurant" + APIVersion.V20250912)
                .content(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));

    verify(restaurantService).getRestaurants();
  }

  @Test
  @DisplayName("Should return 401 Unauthorized when caller has no permissions.")
  public void testGetAllRestaurants_whenCallerHasNoPermissions() throws Exception {
    mockMvc
        .perform(
            get("/api/whatsforlunch/restaurant" + APIVersion.V20250912)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());

    verifyNoInteractions(restaurantService);
  }
}
