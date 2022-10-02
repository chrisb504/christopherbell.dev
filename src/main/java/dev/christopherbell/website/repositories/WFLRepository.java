package dev.christopherbell.website.repositories;

import dev.christopherbell.website.models.wfl.Restaurant;

//@Repository
public interface WFLRepository {// extends CrudRepository<WFLRestaurant, Integer> {
    Restaurant findByName(String name);
}
