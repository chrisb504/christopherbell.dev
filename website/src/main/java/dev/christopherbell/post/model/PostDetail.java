package dev.christopherbell.post.model;

import java.time.Instant;
import lombok.Builder;

/**
 * DTO representing a post returned to API consumers.
 *
 * <p>Contains only presentation‑safe fields; timestamps are server‑generated.</p>
 */
@Builder
public record PostDetail(
    String id,
    String accountId,
    String text,
    Instant createdOn,
    Instant lastUpdatedOn
) {}
