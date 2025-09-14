package dev.christopherbell.libs.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.Test;

public class RequestTest {

  @SuperBuilder
  static class RequestChild extends Request {}

  @Test
  public void requestBuilderTest() {
    var requestId = UUID.randomUUID();
    var request = RequestChild.builder()
        .requestId(requestId)
        .build();

    assertEquals(requestId, request.getRequestId());
  }

  @Test
  public void requestTest() {
    var requestId = UUID.randomUUID();
    var request = RequestChild.builder().build();
    request.setRequestId(requestId);

    assertEquals(requestId, request.getRequestId());
  }
}