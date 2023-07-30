package dev.christopherbell.www.personalsite.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.christopherbell.www.personalsite.models.global.Response;
import dev.christopherbell.www.personalsite.services.WFLService;

@RestController
public class WFLController {
    private final Logger LOG = LoggerFactory.getLogger(WFLController.class);
    private final WFLService wflService;

    @Autowired
    public WFLController(WFLService wflService) {
        this.wflService = wflService;
    }

    /**
     * Takes a id and uses it to get a matching restaurant.
     *
     * @return WFLResponse
     */
    @GetMapping(value = "/api/wfl/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getRestaurantById(HttpServletRequest request, @PathVariable String id) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to return restaurant with id: {}", id);
        }
        var response = this.wflService.getRestaurantById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all restaurants in the database.
     *
     * @return WFLResponse
     */
    @GetMapping(value = "/api/wfl/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getRestaurants(HttpServletRequest request) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to return all restaurants");
        }
        var response = this.wflService.getRestaurants();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}