package com.gateway.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder encoder;

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public Map<String, String> attemptLogin(AuthDto dto) {
        Map<String, String> map = new HashMap<>();
        try {
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            map.put("message:", "Login successful!");
            map.put("token:", jwtUtil.generateTokenFromUsername(dto.getUsername(),
                    userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toSet())));
            return map;
        } catch (BadCredentialsException e) {
            map.put("message:", "Username or password is incorrect");
            return map;
        } catch (Exception e) {
            // Handle other exceptions as necessary
            map.put("message:", "An error occurred during login: " + e.getMessage());
            return map;
        }
    }

    public UserEntity register(UserEntity dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        return repository.save(dto);
    }
}