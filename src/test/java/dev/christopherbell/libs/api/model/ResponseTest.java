package dev.christopherbell.libs.api.model;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResponseTest {

  @Test
  public void testResponseBuilder() {
    var requestId = UUID.randomUUID();
    var response = Response.<String>builder()
        .messages(List.of(Message.builder()
            .code(ApiModelStub.TEST_CODE)
            .description(ApiModelStub.TEST_DESCRIPTION)
            .build()))
        .payload(ApiModelStub.TEST_PAYLOD)
        .requestId(requestId)
        .success(true)
        .build();

    Assertions.assertNotNull(response);
    Assertions.assertEquals(1, response.getMessages().size());
    Assertions.assertNotNull(response.getMessages().getFirst());
    Assertions.assertEquals(ApiModelStub.TEST_CODE, response.getMessages().getFirst().getCode());
    Assertions.assertEquals(ApiModelStub.TEST_DESCRIPTION, response.getMessages().getFirst().getDescription());
    Assertions.assertEquals(ApiModelStub.TEST_PAYLOD, response.getPayload());
    Assertions.assertEquals(requestId, response.getRequestId());
    Assertions.assertTrue(response.isSuccess());
  }

  @Test
  public void testResponseSetters() {
    var requestId = UUID.randomUUID();
    var response = Response.<String>builder().build();
    response.setMessages(List.of(Message.builder()
        .code(ApiModelStub.TEST_CODE)
        .description(ApiModelStub.TEST_DESCRIPTION)
        .build()));
    response.setPayload(ApiModelStub.TEST_PAYLOD);
    response.setRequestId(requestId);
    response.setSuccess(true);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(1, response.getMessages().size());
    Assertions.assertNotNull(response.getMessages().getFirst());
    Assertions.assertEquals(ApiModelStub.TEST_CODE, response.getMessages().getFirst().getCode());
    Assertions.assertEquals(ApiModelStub.TEST_DESCRIPTION, response.getMessages().getFirst().getDescription());
    Assertions.assertEquals(ApiModelStub.TEST_PAYLOD, response.getPayload());
    Assertions.assertEquals(requestId, response.getRequestId());
    Assertions.assertTrue(response.isSuccess());
  }
}