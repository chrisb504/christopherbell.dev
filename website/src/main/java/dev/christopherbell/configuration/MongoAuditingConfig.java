package dev.christopherbell.configuration;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableMongoAuditing(
    auditorAwareRef = "auditorAware",
    dateTimeProviderRef = "auditingDateTimeProvider"
)
public class MongoAuditingConfig {

  /**
   * Provides the current auditor (user) for auditing purposes.
   * This implementation retrieves the username from the Spring Security context.
   *
   * @return an AuditorAware instance that returns the current user's username
   */
  @Bean
  public AuditorAware<String> auditorAware() {
    return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getName);
  }

  /**
   * Provides a Clock bean set to the system's UTC time zone.
   *
   * @return a Clock instance set to UTC
   */
  @Bean
  public Clock clock() {
    return Clock.systemUTC();
  }

  /**
   * Provides the current date and time for auditing purposes.
   * This implementation uses the provided Clock bean to get the current Instant.
   *
   * @param clock the Clock bean to use for getting the current time
   * @return a DateTimeProvider instance that returns the current Instant
   */
  @Bean
  public DateTimeProvider auditingDateTimeProvider(Clock clock) {
    return () -> Optional.of(Instant.now(clock));
  }
}
