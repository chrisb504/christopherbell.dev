package dev.christopherbell.blog.models.global;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class Response implements Serializable {

  private List<Message> messages;
  private String status;
}
