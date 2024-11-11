package dev.christopherbell.libs.common.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Represents the base level request object.
 */
@Data
@SuperBuilder
public abstract class Request {

  @JsonProperty("requestId")
  private UUID requestId;
}