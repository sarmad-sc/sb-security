package com.gateway.controller;

import com.gateway.securityConfig.AuthDto;
import com.gateway.securityConfig.AuthService;
import com.gateway.securityConfig.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class SecurityController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> authenticateUser(@RequestBody AuthDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.attemptLogin(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity dto) {
        return ResponseEntity.ok().body(service.register(dto));
    }
}