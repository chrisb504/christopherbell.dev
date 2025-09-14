package dev.christopherbell.libs.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class MessageTest {

  @Test
  public void testMessageBuilder() {
    var message = Message.builder()
        .code(ApiModelStub.TEST_CODE)
        .description(ApiModelStub.TEST_DESCRIPTION)
        .build();

    assertNotNull(message);
    assertEquals(ApiModelStub.TEST_CODE, message.getCode());
    assertEquals(ApiModelStub.TEST_DESCRIPTION, message.getDescription());
  }

  @Test
  public void testMessageSetters() {
    var message = Message.builder()
        .build();
    message.setCode(ApiModelStub.TEST_CODE);
    message.setDescription(ApiModelStub.TEST_DESCRIPTION);

    assertNotNull(message);
    assertEquals(ApiModelStub.TEST_CODE, message.getCode());
    assertEquals(ApiModelStub.TEST_DESCRIPTION, message.getDescription());
  }
}