package dev.christopherbell.configuration;

import dev.christopherbell.libs.api.APIVersion;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] PUBLIC_URLS = {
      "/",
      "/api/accounts" + APIVersion.V20241215 + "/login",
      "/api/accounts" + APIVersion.V20241215 + "/create",
      "/blog",
      "/css/**",
      "/js/**",
      "/login",
      "/photos",
      "/photos/**",
      "/signup",
      "/thebell/**",
      "/void",
      "/void/**",
      "/wfl"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
      RateLimitFilter rateLimitFilter,
      JwtAuthenticationFilter jwtAuthenticationFilter,
      RequestSizeLimitFilter requestSizeLimitFilter) throws Exception {
    return http
        // Disable CSRF for APIs (use with care)
        .csrf(AbstractHttpConfigurer::disable)

        // Configure authorization rules
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(PUBLIC_URLS).permitAll() // Allow public access to defined URLs
            .anyRequest().authenticated() // Secure all other endpoints
        )

        // Add rate limiting and JWT authentication filters
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(rateLimitFilter, JwtAuthenticationFilter.class)
        .addFilterBefore(requestSizeLimitFilter, RateLimitFilter.class)
        
        // Build the SecurityFilterChain
        .build();
  }

  @Bean
  public RateLimitFilter rateLimitFilter() {
    return new RateLimitFilter();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter(createSkipMatchers(PUBLIC_URLS));
  }

  @Bean
  public RequestSizeLimitFilter requestSizeLimitFilter() {
    return new RequestSizeLimitFilter();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  // Helper method to create a list of AntPathRequestMatchers for JwtAuthenticationFilter
  private List<RequestMatcher> createSkipMatchers(String[] urls) {
    return Arrays.stream(urls)
        .map(AntPathRequestMatcher::new)
        .collect(Collectors.toList());
  }
}
