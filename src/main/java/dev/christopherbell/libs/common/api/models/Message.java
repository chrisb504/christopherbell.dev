package dev.christopherbell.libs.common.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a potential error or any information that will be return to the caller.
 */
@Builder
@Data
public class Message {

  @JsonProperty("code")
  private String code;
  @JsonProperty("description")
  private String description;
}