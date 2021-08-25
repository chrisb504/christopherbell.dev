package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.models.Message;
import dev.christopherbell.azurras.models.blog.BlogResponse;
import dev.christopherbell.azurras.models.whatsforlunch.WFLRequest;
import dev.christopherbell.azurras.models.whatsforlunch.WFLResponse;
import dev.christopherbell.azurras.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WFLService {
    private final Logger LOG = LoggerFactory.getLogger(WFLService.class);

    public WFLService() {}

    public WFLResponse addRestaurant(WFLRequest wflRequest) {
        if (wflRequest == null) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WFLResponse();
    }

    public WFLResponse deleteRestaurant(String restaurantId) {
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WFLResponse();
    }

    public WFLResponse getRandomRestaurant() {
        return new WFLResponse();
    }

    public WFLResponse getRestaurant(String restaurantId) {
        WFLResponse wflResponse = null;
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
             wflResponse = this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return wflResponse;
    }

    public WFLResponse getRestaurants() {
        return new WFLResponse();
    }

    private WFLResponse getErrorWhatsForLunchResponse(String messageDescription, String status) {
        var message = new Message(messageDescription, "");
        return new WFLResponse(message, null, status);
    }
}