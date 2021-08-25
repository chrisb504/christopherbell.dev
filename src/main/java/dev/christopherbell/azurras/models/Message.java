package dev.christopherbell.azurras.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
public class Message {
    private String description;
    private String key;
}
