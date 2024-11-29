package com.cessadev.technical_test_java_spring.security.filters;

import com.cessadev.technical_test_java_spring.security.jwt.JwtUtils;
import com.cessadev.technical_test_java_spring.service.implementation.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsService;

  public JwtAuthorizationFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
    this.jwtUtils = jwtUtils;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {

    String tokenHeader = request.getHeader("Authorization");

    // Only process if there is no previous authentication in context
    if (SecurityContextHolder.getContext().getAuthentication() == null &&
            tokenHeader != null &&
            tokenHeader.startsWith("Bearer ")) {
      String token = tokenHeader.substring(7);

      try {
        if (jwtUtils.isValidToken(token)) {
          String email = jwtUtils.getUsernameFromToken(token);
          UserDetails userDetails = userDetailsService.loadUserByUsername(email);

          UsernamePasswordAuthenticationToken authenticationToken =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + e.getMessage() + "\"}");
        return;
      }
    }

    // Continue with the filter chain
    filterChain.doFilter(request, response);
  }

//    String tokenHeader = request.getHeader("Authorization");
//
//    if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//      String token = tokenHeader.substring(7);
//
//      if (jwtUtils.isValidToken(token)) {
//        String email = jwtUtils.getUsernameFromToken(token);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//      }
//    }
//    filterChain.doFilter(request, response);
//  }


//    String tokenHeader = request.getHeader("Authorization");
//
//    try {
//      if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//        String token = tokenHeader.substring(7);
//
//        if (jwtUtils.isValidToken(token)) {
//          String email = jwtUtils.getUsernameFromToken(token);
//          UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//          UsernamePasswordAuthenticationToken authenticationToken =
//                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//      }
//    } catch (Exception e) {
//      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//      response.setContentType("application/json");
//      response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + e.getMessage() + "\"}");
//      return;
//    }
//
//    filterChain.doFilter(request, response);
//  }
}
