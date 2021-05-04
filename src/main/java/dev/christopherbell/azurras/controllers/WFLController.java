package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.models.whatsforlunch.WFLRequest;
import dev.christopherbell.azurras.models.whatsforlunch.WFLResponse;
import dev.christopherbell.azurras.services.WFLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
    @ResponseBody
    @RequestMapping(value = "/wfl/restaurants/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse addRestaurant(WFLRequest wflRequest) {
        return this.wflService.addRestaurant(wflRequest);
    }

    /**
     * Takes a restaurantId and uses it to delete a 
     * restaurant from the database.
     * @param restaurantId
     * @return WFLResponse
     */
    @ResponseBody
    @RequestMapping(value = "/wfl/restaurants/delete/{restaurantId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse deleteRestaurant(String restaurantId) {
        return this.wflService.deleteRestaurant(restaurantId);
    }

    /**
     * Takes a restaurantId and uses it to
     * get a matching restaurant.
     * 
     * @return WFLResponse
     */
    @ResponseBody
    @RequestMapping(value = "/whatsforlunch/restaurant/{restaurantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse getRestaurant(String restaurantId) {
        return this.wflService.getRestaurant(restaurantId);
    }

    /**
     * Returns all restaurants in the database.
     * 
     * @return WFLResponse
     */
    @ResponseBody
    @RequestMapping(value = "/whatsforlunch/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WFLResponse getRestaurants() {
        return this.wflService.getRestaurants();
    }

    /**
     * Returns the html for the WFL add page.
     * 
     * @return html page
     */
    @RequestMapping(value = "/whatsforlunch/add", method = RequestMethod.GET)
    public String getWhatsForLunchAddPage() {
        return "whatsforlunchAdd";
    }

    /**
     * Returns the html for the WFL delete page.
     * 
     * @return html page
     */
    @RequestMapping(value = "/whatsforlunch/delete", method = RequestMethod.GET)
    public String getWhatsForLunchDeletePage() {
        return "whatsforlunchDelete";
    }

    /**
     * Returns the WFL home page.
     * 
     * @return html page
     */
    @RequestMapping(value = "/whatsforlunch", method = RequestMethod.GET)
    public String getWhatsForLunchPage() {
        return "whatsforlunch";
    }

}