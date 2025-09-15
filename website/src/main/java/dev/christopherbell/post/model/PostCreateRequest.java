package dev.christopherbell.post.model;

import lombok.Builder;

@Builder
public record PostCreateRequest(
    String text
) {}
