package com.SpingbootApp.service;

import com.SpingbootApp.AuthResponseEntity.TokenValidationResponse;
import com.SpingbootApp.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthService(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public TokenValidationResponse validateToken(String token) {
        try {
            String username = jwtUtil.extractUsername(token);

            System.out.println("Extracted Username: " + username);
            if (username == null || username.isEmpty()) {
                System.out.println("Failed to extract username from token.");
                return new TokenValidationResponse(false, null);
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("Loaded UserDetails: " + userDetails.getUsername());

            boolean isValid = jwtUtil.validateToken(token, userDetails);
            System.out.println("Token Valid: " + isValid);

            return new TokenValidationResponse(isValid, isValid ? username : null);
        } catch (Exception e) {
            System.out.println("Token validation error: " + e.getMessage());
            return new TokenValidationResponse(false, null);
        }
    }
}
