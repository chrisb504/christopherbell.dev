package dev.christopherbell.www.personalsite.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.christopherbell.www.personalsite.configs.Constants;
import dev.christopherbell.www.personalsite.configs.properties.WFLProperties;
import dev.christopherbell.www.personalsite.models.global.Message;
import dev.christopherbell.www.personalsite.models.wfl.Restaurant;
import dev.christopherbell.www.personalsite.models.wfl.WFLResponse;

@Service
public class WFLService {
    private final Logger LOG = LoggerFactory.getLogger(WFLService.class);
    private final WFLProperties wflProperties;

    public WFLService(WFLProperties wflProperties) {
        this.wflProperties = wflProperties;
    }

    public WFLResponse getRestaurantOfTheDay() {
        return new WFLResponse(new Restaurant(), null, null);
    }

    public WFLResponse getRestaurantById(String id) {
        if(Objects.isNull(id) || id.isBlank()) {
            final var message = new Message("WFLService.getRestaurants.InvalidId", "Given id is blank, empty or null");
            LOG.error(message.getDescription());
            final var messages = Arrays.asList(message);
            return new WFLResponse(new Restaurant(), messages, Constants.STATUS_FAILURE);
        }
        final var restaurants = this.wflProperties.getRestaurants();
        if(Objects.isNull(restaurants)) {
            final var message = new Message("WFLService.getRestaurants.NoResults", "No Restaurants found in the config file.");
            LOG.error(message.getDescription());
            final var messages = Arrays.asList(message);
            return new WFLResponse(new Restaurant(), messages, Constants.STATUS_FAILURE);
        }
        Restaurant restaurant = null;
        for(Restaurant rest: restaurants) {
            if(rest.getId().equals(Integer.parseInt(id))) {
                restaurant = rest;
            }
        }

        return new WFLResponse(restaurant, null, Constants.STATUS_SUCCESS);
    }

    public WFLResponse getRestaurants() {
        final var restaurants = this.wflProperties.getRestaurants();
        if(Objects.isNull(restaurants)) {
            final var message = new Message("WFLService.getRestaurants.NoResults", "No Restaurants found in the config file.");
            LOG.error(message.getDescription());
            final var messages = Arrays.asList(message);
            return new WFLResponse(new ArrayList<>(), messages, Constants.STATUS_FAILURE);
        }
        return new WFLResponse(restaurants, null, null);
    }
}