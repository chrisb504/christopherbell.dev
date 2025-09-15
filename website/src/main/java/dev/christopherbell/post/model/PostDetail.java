package dev.christopherbell.post.model;

import java.time.Instant;
import lombok.Builder;

@Builder
public record PostDetail(
    String id,
    String accountId,
    String text,
    Instant createdOn,
    Instant lastUpdatedOn
) {}
