package dev.christopherbell.configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.core.annotation.Order;

/**
 * Simple global rate limiting filter using Bucket4j.
 */
@Order(1)
public class RateLimitFilter extends OncePerRequestFilter {

  private final Bucket bucket;

  /**
   * Creates a filter with default limit of 5 requests per minute.
   */
  public RateLimitFilter() {
    this(Bucket4j.builder()
        .addLimit(Bandwidth.simple(5, Duration.ofMinutes(1)))
        .build());
  }

  /**
   * Creates a filter with a custom bucket. Intended for testing.
   */
  public RateLimitFilter(Bucket bucket) {
    this.bucket = bucket;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (bucket.tryConsume(1)) {
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    }
  }
}
