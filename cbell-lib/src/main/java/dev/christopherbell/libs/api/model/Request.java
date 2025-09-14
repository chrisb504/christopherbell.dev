package dev.christopherbell.libs.api.model;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Base class for API request models.
 */
@Data
@SuperBuilder(toBuilder = true)
public abstract class Request {

  private UUID requestId;
}
