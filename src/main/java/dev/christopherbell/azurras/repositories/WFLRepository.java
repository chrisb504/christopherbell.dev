package dev.christopherbell.azurras.repositories;

import dev.christopherbell.azurras.models.whatsforlunch.WFLRestaurant;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WFLRepository {//extends CrudRepository<WFLRestaurant, Integer> {
    WFLRestaurant findByName(String name);
}
