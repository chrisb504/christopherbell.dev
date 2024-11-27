package dev.christopherbell.configuration;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
//        .securityMatcher("/api/**")
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/",
                "/blog",
                "/void",
                "/void/**",
                "/void/login",
                "/void/signup",
                "/api/permission/v1/getToken",
                "/js/**",
                "/css/**"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(new JwtAuthenticationFilter(List.of(
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/blog"),
            new AntPathRequestMatcher("/void"),
            new AntPathRequestMatcher("/void/**"),
            new AntPathRequestMatcher("/void/login"),
            new AntPathRequestMatcher("/void/signup"),
            new AntPathRequestMatcher("/api/permission/v1/getToken"),
            new AntPathRequestMatcher("/js/**"),
            new AntPathRequestMatcher("/css/**")
        )), UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
