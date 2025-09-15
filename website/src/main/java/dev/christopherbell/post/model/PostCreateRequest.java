package dev.christopherbell.post.model;

import lombok.Builder;

/**
 * Request payload for creating a new post.
 *
 * @param text the tweet‑like content to publish (trimmed, required, ≤ 280 chars)
 */
@Builder
public record PostCreateRequest(
    String text
) {}
