package dev.christopherbell.www.personalsite.models.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable {
    private String code;
    private String description;
}
