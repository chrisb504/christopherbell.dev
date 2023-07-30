package dev.christopherbell.www.personalsite.repositories;

import dev.christopherbell.www.personalsite.models.wfl.Restaurant;

//@Repository
public interface WFLRepository {// extends CrudRepository<WFLRestaurant, Integer> {
    Restaurant findByName(String name);
}
