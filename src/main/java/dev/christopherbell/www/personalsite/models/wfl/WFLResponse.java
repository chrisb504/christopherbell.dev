package dev.christopherbell.www.personalsite.models.wfl;

import lombok.Getter;
import java.util.List;

import dev.christopherbell.www.personalsite.models.global.Message;
import dev.christopherbell.www.personalsite.models.global.Response;

@Getter
public class WFLResponse extends Response {
    private List<Restaurant> restaurants;
    private Restaurant restaurant;

    public WFLResponse(List<Restaurant> restaurants, List<Message> messages, String status) {
        super(messages, status);
        this.restaurants = restaurants;
    }

    public WFLResponse(Restaurant restaurant, List<Message> messages, String status) {
        super(messages, status);
        this.restaurant = restaurant;
    }
}