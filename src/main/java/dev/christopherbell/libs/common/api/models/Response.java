package dev.christopherbell.libs.common.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * This class represents a base level response class with common fields that every response should contain.
 */
@Data
@SuperBuilder
public class Response<T> {

  @JsonProperty("messages")
  private List<Message> messages;
  @JsonProperty("payload")
  private T payload;
  @JsonProperty("requestId")
  private UUID requestId;
  @JsonProperty("success")
  private boolean success;
}