package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.models.whatsforlunch.WFLRequest;
import dev.christopherbell.azurras.models.whatsforlunch.WFLResponse;
import dev.christopherbell.azurras.services.WFLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WFLController {
    private final Logger LOG = LoggerFactory.getLogger(WFLController.class);
    private final WFLService wflService;

    @Autowired
    public WFLController(final WFLService wflService) {
        this.wflService = wflService;
    }

    /**
     * Adds a new restaurant to the database.
     * @return WFLResponse
     */
    @PostMapping(value = "/wfl/restaurants/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse addRestaurant(WFLRequest wflRequest) {
        return this.wflService.addRestaurant(wflRequest);
    }

    /**
     * Takes a restaurantId and uses it to delete a 
     * restaurant from the database.
     * @param restaurantId
     * @return WFLResponse
     */
    @PostMapping(value = "/wfl/restaurants/delete/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse deleteRestaurant(String restaurantId) {
        return this.wflService.deleteRestaurant(restaurantId);
    }

    /**
     * Takes a restaurantId and uses it to
     * get a matching restaurant.
     * 
     * @return WFLResponse
     */
    @GetMapping(value = "/wfl/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse getRestaurant(String restaurantId) {
        return this.wflService.getRestaurant(restaurantId);
    }

    /**
     * Returns all restaurants in the database.
     * 
     * @return WFLResponse
     */
    @GetMapping(value = "/wfl/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse getRestaurants() {
        return this.wflService.getRestaurants();
    }

    /**
     * Returns the html for the WFL add page.
     * 
     * @return html page
     */
    @GetMapping(value = "/wfl/add")
    public String getWhatsForLunchAddPage() {
        return "whatsforlunchAdd";
    }

    /**
     * Returns the html for the WFL delete page.
     * 
     * @return html page
     */
    @GetMapping(value = "/wfl/delete")
    public String getWhatsForLunchDeletePage() {
        return "whatsforlunchDelete";
    }

    /**
     * Returns the WFL home page.
     * 
     * @return html page
     */
    @GetMapping(value = "/wfl")
    public String getWhatsForLunchPage() {
        return "whatsforlunch";
    }

}