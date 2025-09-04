package dev.christopherbell.account.model;

/**
 * Enum representing the status of an account.
 */
public enum AccountStatus {
  /**
   * Account is active and in good standing.
   */
  ACTIVE,
  /**
   * Account is inactive and cannot be used until reactivated.
   */
  INACTIVE,
  /**
   * Account is suspended due to violations or other issues.
   */
  SUSPENDED
}
