package com.cessadev.technical_test_java_spring.security;

import com.cessadev.technical_test_java_spring.security.filters.JwtAuthenticationFilter;
import com.cessadev.technical_test_java_spring.security.filters.JwtAuthorizationFilter;
import com.cessadev.technical_test_java_spring.security.jwt.JwtUtils;
import com.cessadev.technical_test_java_spring.service.implementation.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsService;
  private final JwtAuthorizationFilter jwtAuthorizationFilter;

  public SecurityConfig(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
    this.jwtUtils = jwtUtils;
    this.userDetailsService = userDetailsService;
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils, authenticationManager(httpSecurity));
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");

    return httpSecurity
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> {
              auth.requestMatchers("/api/v1/users/create").permitAll();
              auth.anyRequest().authenticated();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    return authenticationManagerBuilder.build();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return (request, response, authException) -> {
      log.error("Authentication error: {}", authException.getMessage());
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      Map<String, String> error = Map.of("error", "Unauthorized", "message", authException.getMessage());
      response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    };
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    configuration.setExposedHeaders(List.of("Authorization"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
