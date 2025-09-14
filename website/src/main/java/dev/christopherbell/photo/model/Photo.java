package dev.christopherbell.photo.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Photo metadata used by the gallery.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Photo {

  private Instant createdOn;
  private String description;
  private UUID id;
  private String name;
  private String path;
}
