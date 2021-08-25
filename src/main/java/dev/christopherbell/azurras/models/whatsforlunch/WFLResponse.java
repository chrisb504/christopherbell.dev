package dev.christopherbell.azurras.models.whatsforlunch;

import dev.christopherbell.azurras.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WFLResponse {
    private Message message;
    private List<WFLRestaurant> payload;
    private String status;
}