package dev.christopherbell.post.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document representing a tweet‑like post authored by an account.
 *
 * <p>Posts are stored in the separate {@code posts} collection and linked back
 * to the owning account via {@link #accountId}. Content is modeled as short
 * text suitable for micro‑blogging.</p>
 */
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Document("posts")
public class Post {
  private final String type = "post";

  /** Unique post identifier (UUID string). */
  @Id private String id;

  /** Owning account's identifier. */
  private String accountId;
  // Tweet-like short message body (trimmed, <= 280 chars)
  private String text;

  /** Identifier of the root post in the thread (self for top-level posts). */
  private String rootId;
  /** Identifier of the direct parent post (null for top-level posts). */
  private String parentId;
  /** Depth within the thread: 0 for root, 1 for a reply, etc. */
  private Integer level;

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

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS'Z'",
      timezone = "UTC")
  private Instant expiresOn;

  // Likes
  /** Set of account IDs that liked this post. */
  private Set<String> likedBy;
  /** Precomputed number of likes for display (kept in sync with {@link #likedBy}). */
  private Integer likesCount;
}
