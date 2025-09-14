package dev.christopherbell.libs.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message entry included in API responses for errors or informational notes.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Message {

  private String code;
  private String description;
}
