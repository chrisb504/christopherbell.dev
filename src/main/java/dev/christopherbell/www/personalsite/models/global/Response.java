package dev.christopherbell.www.personalsite.models.global;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private List<Message> messages;
    private String status;
}
