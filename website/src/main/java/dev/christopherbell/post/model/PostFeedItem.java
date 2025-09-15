package dev.christopherbell.post.model;

import java.time.Instant;
import lombok.Builder;

/**
 * DTO representing a post in the global feed, including the author's username.
 */
@Builder
public record PostFeedItem(
    String id,
    String accountId,
    String username,
    String text,
    Instant createdOn,
    Instant lastUpdatedOn
) {}

