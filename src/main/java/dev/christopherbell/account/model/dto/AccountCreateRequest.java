package dev.christopherbell.account.model.dto;

import lombok.Builder;

/**
 * Request object for creating a new account.
 *
 * @param firstName The first name of the account holder.
 * @param lastName  The last name of the account holder.
 * @param email     The email address of the account holder.
 * @param password  The password for the account.
 * @param username  The desired username for the account.
 */
@Builder
public record AccountCreateRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    String username
) {}
