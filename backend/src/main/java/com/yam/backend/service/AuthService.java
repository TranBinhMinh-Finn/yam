package com.yam.backend.service;

import com.yam.backend.model.dto.request.LoginDTO;
import com.yam.backend.model.dto.request.RegisterDTO;
import com.yam.backend.model.dto.response.AuthResponseDTO;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthResponseDTO register(RegisterDTO registerDTO) {
        User user = userService.register(registerDTO);
        String accessKey = jwtService.generateAccessToken(user);
        String refreshKey = jwtService.generateRefreshToken(user);

        return AuthResponseDTO.builder()
                .accessToken(accessKey)
                .refreshToken(refreshKey)
                .build();
    }

    public AuthResponseDTO login(LoginDTO loginDTO) {
        User user = userRepository.findUserByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found"));

        var isCorrect = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!isCorrect) {
            throw new BadCredentialsException("Incorrect Email/Password");
        }

        String accessKey = jwtService.generateAccessToken(user);
        String refreshKey = jwtService.generateRefreshToken(user);

        return AuthResponseDTO.builder()
                .accessToken(accessKey)
                .refreshToken(refreshKey)
                .build();
    }
}
