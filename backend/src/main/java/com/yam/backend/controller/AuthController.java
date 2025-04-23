package com.yam.backend.controller;

import com.yam.backend.model.dto.request.LoginDTO;
import com.yam.backend.model.dto.request.RefreshTokenDTO;
import com.yam.backend.model.dto.request.RegisterDTO;
import com.yam.backend.model.dto.response.AuthResponseDTO;
import com.yam.backend.model.dto.response.RefreshTokenResponseDTO;
import com.yam.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        AuthResponseDTO responseDTO = authService.login(loginDTO);
        return ResponseEntity.ok()
                .body(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        AuthResponseDTO responseDTO = authService.register(registerDTO);
        return ResponseEntity.ok()
                .body(responseDTO);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        String newAccessToken = authService.refreshAccessToken(refreshTokenDTO.getRefreshToken());
        return ResponseEntity.ok()
                .body(RefreshTokenResponseDTO.builder()
                        .accessToken(newAccessToken)
                        .build());
    }
}
