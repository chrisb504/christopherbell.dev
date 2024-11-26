package dev.christopherbell.thevoid.cry.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Cry {

  private long id;
  private String creationDate;
  private String expirationDate;
  private boolean isRootCry;
  private String lastAmplifiedDate;
  private String text;
  private String author;
  private String authorAccountId;
}
