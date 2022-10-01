package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.configs.Constants;
import dev.christopherbell.azurras.models.global.Message;
import dev.christopherbell.azurras.models.wfl.WFLRequest;
import dev.christopherbell.azurras.models.wfl.WFLResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WFLService {
    private final Logger LOG = LoggerFactory.getLogger(WFLService.class);

    public WFLService() {
    }

    public WFLResponse addRestaurant(WFLRequest wflRequest) {
        if (wflRequest == null) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WFLResponse(null, null, null);
    }

    public WFLResponse deleteRestaurant(String restaurantId) {
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
            this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new WFLResponse(null, null, null);
    }

    public WFLResponse getRandomRestaurant() {
        return new WFLResponse(null, null, null);
    }

    public WFLResponse getRestaurant(String restaurantId) {
        WFLResponse wflResponse = null;
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
            wflResponse = this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST,
                    String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return wflResponse;
    }

    public WFLResponse getRestaurants() {
        return new WFLResponse(null, null, null);
    }

    private WFLResponse getErrorWhatsForLunchResponse(String messageDescription, String status) {
        var message = new Message(messageDescription, "");
        // return new WFLResponse(message, null, status);
        return new WFLResponse(null, null, null);
    }
}