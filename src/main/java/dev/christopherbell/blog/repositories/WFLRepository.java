package dev.christopherbell.blog.repositories;

import dev.christopherbell.blog.models.wfl.Restaurant;

//@Repository
public interface WFLRepository {// extends CrudRepository<WFLRestaurant, Integer> {
    Restaurant findByName(String name);
}
