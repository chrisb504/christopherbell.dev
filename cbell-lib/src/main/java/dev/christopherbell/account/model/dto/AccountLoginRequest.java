package dev.christopherbell.account.model.dto;

import lombok.Builder;

/**
 * DTO for account login requests.
 */
@Builder
public record AccountLoginRequest(
    String email,
    String password
) {}
