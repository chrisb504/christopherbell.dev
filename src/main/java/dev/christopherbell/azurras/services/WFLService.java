package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.models.whatsforlunch.WFLRequest;
import dev.christopherbell.azurras.models.whatsforlunch.WFLResponse;
import dev.christopherbell.azurras.utils.Constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WFLService {
    private final Log LOG = LogFactory.getLog(WFLService.class);

    public WFLService() {

    }

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

    public WFLResponse getRandomResturant() {
        return new WFLResponse();
    }

    public WFLResponse getRestaurant(String restaurantId) {
        WFLResponse wflResponse = null;
        if (restaurantId == null || restaurantId.isEmpty()) {
            LOG.error(Constants.NULL_REQUEST);
             wflResponse = this.getErrorWhatsForLunchResponse(Constants.NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        } else {

        }
        return wflResponse;
    }

    public WFLResponse getRestaurants() {
        return new WFLResponse();
    }

    private WFLResponse getErrorWhatsForLunchResponse(String message, String status) {
        WFLResponse blogResponse = new WFLResponse();
        blogResponse.setMessage(message);
        blogResponse.setStatus(status);
        return blogResponse;
    }
}