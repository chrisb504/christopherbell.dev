package dev.christopherbell.website.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.christopherbell.website.models.global.Response;
import dev.christopherbell.website.services.WFLService;

@RestController
public class WFLController {
    private final Logger LOG = LoggerFactory.getLogger(WFLController.class);
    private final WFLService wflService;

    @Autowired
    public WFLController(final WFLService wflService) {
        this.wflService = wflService;
    }

    /**
     * Takes a id and uses it to get a matching restaurant.
     *
     * @return WFLResponse
     */
    @GetMapping(value = "/api/wfl/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getRestaurant(String id) {
        var response = this.wflService.getRestaurant(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all restaurants in the database.
     *
     * @return WFLResponse
     */
    @GetMapping(value = "/api/wfl/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getRestaurants() {
        var response = this.wflService.getRestaurants();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}