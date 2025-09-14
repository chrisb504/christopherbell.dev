package dev.christopherbell.account.model;

import lombok.Builder;

/**
 * DTO for account login requests.
 */
@Builder
public record AccountLoginRequest(
    String email,
    String password
) {}
