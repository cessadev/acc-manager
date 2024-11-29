package com.cessadev.technical_test_java_spring.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtils {

  @Value("${jwt.secret.key}")
  private String secretKey;

  @Value("${jwt.expiration.time}")
  private Long expirationTime;

  /**
   * Generate an access token for the given username.
   *
   * @param username The username to include in the token's subject.
   * @return The generated JWT token as a String.
   */
  public String generateAccessToken(String username) {
    return Jwts.builder()
            .setSubject(username) // Subject of the token
            .setIssuedAt(new Date()) // Creation date
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Expiration time
            .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Signing algorithm
            .compact();
  }

  /**
   * Validate the token by parsing its claims and checking its expiration.
   *
   * @param token The JWT token to validate.
   * @return true if the token is valid, false otherwise.
   */
  public boolean isValidToken(String token) {
    try {
      extractAllClaims(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.error("Token expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("Unsupported JWT: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Malformed JWT: {}", e.getMessage());
    } catch (SignatureException e) {
      log.error("Invalid signature: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument token: {}", e.getMessage());
    }
    return false;
  }

  /**
   * Extract all claims from a token.
   *
   * @param token The JWT token to parse.
   * @return Claims object containing the token's details.
   */
  public Claims extractAllClaims(String token) {
    return Jwts.parser()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  /**
   * Extract a specific claim from the token using a provided function.
   *
   * @param token The JWT token to parse.
   * @param claimsResolver A function to resolve the specific claim.
   * @param <T> The type of the claim being extracted.
   * @return The extracted claim value.
   */
  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Extract the username (subject) from the token.
   *
   * @param token The JWT token to parse.
   * @return The username as a String.
   */
  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  /**
   * Create a signing key for the JWT using the secret key from application properties.
   *
   * @return The SecretKey object for signing.
   */
  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
