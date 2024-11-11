package dev.christopherbell.thevoid.models.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InviteCode {

  private String code;
}
