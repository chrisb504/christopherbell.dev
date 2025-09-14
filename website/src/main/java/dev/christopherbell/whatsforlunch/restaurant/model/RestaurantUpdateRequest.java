package dev.christopherbell.whatsforlunch.restaurant.model;

import lombok.Builder;

/**
 * A request to update a restaurant.
 *
 * @param id          the unique identifier of the restaurant
 * @param name        the name of the restaurant
 * @param address     the address of the restaurant
 * @param phoneNumber the phone number of the restaurant
 * @param website     the website of the restaurant
 */
@Builder
public record RestaurantUpdateRequest(
    String id,
    String name,
    Address address,
    String phoneNumber,
    String website
) {}
