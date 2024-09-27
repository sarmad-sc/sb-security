package com.gateway.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class SecurityController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok().body("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    @PostMapping("/test")
    public ResponseEntity<String> authenticateUser() {
        System.out.println("login hit..................");
        return ResponseEntity.ok().body("Login successful");
    }
}

@Data
class LoginRequest {
    private String username;
    private String password;
}