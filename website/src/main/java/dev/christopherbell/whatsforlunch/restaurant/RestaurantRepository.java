package dev.christopherbell.whatsforlunch.restaurant;

import dev.christopherbell.whatsforlunch.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {}
