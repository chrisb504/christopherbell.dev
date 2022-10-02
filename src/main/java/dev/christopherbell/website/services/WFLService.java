package dev.christopherbell.website.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.christopherbell.website.configs.Constants;
import dev.christopherbell.website.configs.properties.WFLProperties;
import dev.christopherbell.website.models.global.Message;
import dev.christopherbell.website.models.wfl.WFLRequest;
import dev.christopherbell.website.models.wfl.WFLResponse;

@Service
public class WFLService {
    private final Logger LOG = LoggerFactory.getLogger(WFLService.class);
    private final WFLProperties wflProperties;

    public WFLService(WFLProperties wflProperties) {
        this.wflProperties = wflProperties;
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