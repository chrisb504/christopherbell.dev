package dev.christopherbell.libs.common.api.model;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Represents the base level request object.
 */
@Data
@SuperBuilder(toBuilder = true)
public abstract class Request {

  private UUID requestId;
}