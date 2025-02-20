package com.SpingbootApp.AuthResponseEntity;

public class TokenValidationResponse {
    private boolean valid;
    private String username;

    public TokenValidationResponse(boolean valid, String username) {
        this.valid = valid;
        this.username = username;
    }

    public boolean isValid() {
        return valid;
    }

    public String getUsername() {
        return username;
    }
}