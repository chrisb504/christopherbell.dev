package com.azurras.website.controllers;

import com.azurras.website.models.whatsforlunch.WhatsForLunchRequest;
import com.azurras.website.models.whatsforlunch.WhatsForLunchResponse;
import com.azurras.website.services.WhatsForLunchService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WhatsForLunchController {
    private final Log LOG = LogFactory.getLog(WhatsForLunchController.class);
    private WhatsForLunchService whatsForLunchService;

    @Autowired
    public WhatsForLunchController(final WhatsForLunchService whatsForLunchService) {
        this.whatsForLunchService = whatsForLunchService;
    }

    /**
     * Adds a new restaurant to the database.
     * @return WhatsForLunchResponse
     */
    @ResponseBody
    @RequestMapping(value = "/whatsforlunch/restaurants/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WhatsForLunchResponse addRestaurant(WhatsForLunchRequest whatsForLunchRequest) {
        return this.whatsForLunchService.addRestaurant(whatsForLunchRequest);
    }

    /**
     * Takes a restaurantId and uses it to delete a 
     * restaurant from the database.
     * @param blogPostId
     * @return WhatsForLunchResponse
     */
    @ResponseBody
    @RequestMapping(value = "/whatsforlunch/restaurants/delete/{restaurantId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WhatsForLunchResponse deleteRestaurant(String restaurantId) {
        return this.whatsForLunchService.deleteRestaurant(restaurantId);
    }

    /**
     * Takes a restaurantId and uses it to
     * get a matching restaurant.
     * 
     * @return WhatsForLunchResponse
     */
    @ResponseBody
    @RequestMapping(value = "/whatsforlunch/restaurant/{restaurantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WhatsForLunchResponse getRestaurant(String restaurantId) {
        return this.whatsForLunchService.getRestaurant(restaurantId);
    }

    /**
     * Returns all restaurants in the database.
     * 
     * @return WhatsForLunchResponse
     */
    @ResponseBody
    @RequestMapping(value = "/whatsforlunch/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WhatsForLunchResponse getRestaurants() {
        return this.whatsForLunchService.getRestaurants();
    }

    /**
     * Returns the html for the whatsforlunch add page.
     * 
     * @return whatsforlunchAdd
     */
    @RequestMapping(value = "/whatsforlunch/add", method = RequestMethod.GET)
    public String getWhatsForLunchAddPage() {
        return "whatsforlunchAdd";
    }

    /**
     * Returns the html for the whatsforlunch delete page.
     * 
     * @return whatsforlunchDelete
     */
    @RequestMapping(value = "/whatsforlunch/delete", method = RequestMethod.GET)
    public String getWhatsForLunchDeletePage() {
        return "whatsforlunchDelete";
    }

    /**
     * Returns the whatsforlunch page.
     * 
     * @return whatsforlunch
     */
    @RequestMapping(value = "/whatsforlunch", method = RequestMethod.GET)
    public String getWhatsForLunchPage() {
        return "whatsforlunch";
    }

}