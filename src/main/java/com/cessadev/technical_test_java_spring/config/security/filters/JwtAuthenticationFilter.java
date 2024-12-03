package com.cessadev.technical_test_java_spring.config.security.filters;

import com.cessadev.technical_test_java_spring.model.UserModel;
import com.cessadev.technical_test_java_spring.config.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtUtils jwtUtils;

  public JwtAuthenticationFilter(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
    this.jwtUtils = jwtUtils;
    setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {
    try {
      if (!request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
        throw new AuthenticationServiceException("Invalid content type. Expected application/json");
      }

      UserModel userModel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
      UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword());

      return getAuthenticationManager().authenticate(authenticationToken);
    } catch (IOException e) {
      throw new AuthenticationServiceException("Error parsing user credentials", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();
    String token = jwtUtils.generateAccessToken(user.getUsername());

    response.setHeader("Authorization", "Bearer " + token);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    Map<String, Object> httpResponse = new HashMap<>();
    httpResponse.put("token", token);
    httpResponse.put("message", "Authentication successful");
    httpResponse.put("username", user.getUsername());

    response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
    response.setStatus(HttpStatus.OK.value());
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException failed) throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("error", "Authentication failed");
    errorResponse.put("message", failed.getMessage());

    response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    response.getWriter().flush();
  }
}
