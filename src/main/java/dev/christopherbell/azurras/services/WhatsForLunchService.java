package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.models.whatsforlunch.WhatsForLunchRequest;
import dev.christopherbell.azurras.models.whatsforlunch.WhatsForLunchResponse;
import dev.christopherbell.azurras.utils.Constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WhatsForLunchService {
    private final Log LOG = LogFactory.getLog(WhatsForLunchService.class);

    public WhatsForLunchService() {

    }

    public WhatsForLunchResponse addRestaurant(WhatsForLunchRequest whatsForLunchRequest) {
        if (whatsForLunchRequest == null) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WhatsForLunchResponse();
    }

    public WhatsForLunchResponse deleteRestaurant(String restaurantId) {
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WhatsForLunchResponse();
    }

    public WhatsForLunchResponse getRandomResturant() {
        return new WhatsForLunchResponse();
    }

    public WhatsForLunchResponse getRestaurant(String restaurantId) {
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WhatsForLunchResponse();
    }

    public WhatsForLunchResponse getRestaurants() {
        return new WhatsForLunchResponse();
    }

    private WhatsForLunchResponse getErrorWhatsForLunchResponse(String message, String status) {
        WhatsForLunchResponse blogResponse = new WhatsForLunchResponse();
        blogResponse.setMessage(message);
        blogResponse.setStatus(status);
        return blogResponse;
    }
}