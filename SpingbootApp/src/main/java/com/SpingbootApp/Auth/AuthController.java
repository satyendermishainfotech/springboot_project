package com.SpingbootApp.Auth;

import com.SpingbootApp.AuthResponseEntity.TokenValidationRequest;
import com.SpingbootApp.AuthResponseEntity.TokenValidationResponse;
import com.SpingbootApp.JwtUtil;
import com.SpingbootApp.entity.User;
import com.SpingbootApp.service.AddUserService;
import com.SpingbootApp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    public AddUserService addUserService;
    private AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService, AddUserService addUserService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.addUserService = addUserService;
        this.authService = authService;
    }
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        // Create and save the new user
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword()); // You should hash the password before saving
        addUserService.saveUser(newUser); // Save the user using UserService
        return "User added successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return new ResponseEntity<>(new AuthResponse(jwtUtil.generateToken(userDetails)), HttpStatus.OK);
    }
    @PostMapping("/validate-token")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        System.out.println("Extracted Token:"+token);
        TokenValidationResponse response = authService.validateToken(token);
        return ResponseEntity.ok(response);
    }
}

class AuthRequest {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
class AuthResponse {
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}



