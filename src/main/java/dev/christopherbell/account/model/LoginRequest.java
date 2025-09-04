package dev.christopherbell.account.model;

import lombok.Builder;

@Builder
public record LoginRequest(String email, String password) {}
