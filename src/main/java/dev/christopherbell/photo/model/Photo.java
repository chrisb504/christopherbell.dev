package dev.christopherbell.photo.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Photo {

  private Instant createdOn;
  private String description;
  private UUID id;
  private String name;
  private String path;
}
