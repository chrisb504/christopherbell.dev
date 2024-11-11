package dev.christopherbell.libs.common.api.models;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a potential error or any information that will be return to the caller.
 */
@Builder
@Data
public class Message {

  private String code;
  private String description;
}