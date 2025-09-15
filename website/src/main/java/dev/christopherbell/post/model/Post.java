package dev.christopherbell.post.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Document("posts")
public class Post {
  private final String type = "post";

  @Id private String id;

  private String accountId;
  // Tweet-like short message body (trimmed, <= 280 chars)
  private String text;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS'Z'",
      timezone = "UTC")
  @CreatedDate
  private Instant createdOn;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS'Z'",
      timezone = "UTC")
  @LastModifiedDate
  private Instant lastUpdatedOn;
}
