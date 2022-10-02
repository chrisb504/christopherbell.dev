package dev.christopherbell.website.models.wfl;

import lombok.Getter;
import java.util.List;

import dev.christopherbell.website.models.global.Message;
import dev.christopherbell.website.models.global.Response;

@Getter
public class WFLResponse extends Response {
    private List<Restaurant> restaurants;

    public WFLResponse(List<Restaurant> restaurants, List<Message> messages, String status) {
        super(messages, status);
        this.restaurants = restaurants;
    }
}