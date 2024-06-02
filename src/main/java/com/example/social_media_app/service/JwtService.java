package com.example.social_media_app.service;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    boolean isValidToken(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    String extractUserName(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
