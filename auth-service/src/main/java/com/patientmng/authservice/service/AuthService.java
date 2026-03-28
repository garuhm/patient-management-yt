package com.patientmng.authservice.service;

import com.patientmng.authservice.dto.LoginRequestDTO;
import com.patientmng.authservice.model.User;
import com.patientmng.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public Optional<String> authenticate(LoginRequestDTO requestDTO) {
        Optional<String> token = userService.findByEmail(requestDTO.email())
                .filter(u -> passwordEncoder.matches(requestDTO.password(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    public boolean validateToken(String substring) {
        try {
            jwtUtil.validateToken(substring);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
