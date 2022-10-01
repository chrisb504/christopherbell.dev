package dev.christopherbell.azurras.repositories;

import dev.christopherbell.azurras.models.wfl.Restaurant;

//@Repository
public interface WFLRepository {// extends CrudRepository<WFLRestaurant, Integer> {
    Restaurant findByName(String name);
}
