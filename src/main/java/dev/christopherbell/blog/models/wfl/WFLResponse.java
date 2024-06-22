package dev.christopherbell.blog.models.wfl;

import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.global.Response;
import lombok.Getter;
import java.util.List;

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