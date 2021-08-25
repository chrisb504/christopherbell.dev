package dev.christopherbell.azurras.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    private String description;
    private String key;
}
