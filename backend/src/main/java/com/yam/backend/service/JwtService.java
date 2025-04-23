package com.yam.backend.service;

import com.yam.backend.model.user.User;
import com.yam.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final UserRepository userRepository;

    @Value("${security.access-key-expire-time}")
    int accessKeyExpireTime;

    @Value("${security.refresh-key-expire-time}")
    int refreshKeyExpireTime;

    @Value("${security.token-issuer}")
    String tokenIssuer;

    public String generateAccessToken(User user) {
        return generateToken(user, accessKeyExpireTime);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, refreshKeyExpireTime);
    }

    private String generateToken(User user, int expiresIn) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(expiresIn);

        var claims = JwtClaimsSet.builder()
                .issuer(tokenIssuer)
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("scope", user.getRole())
                .subject(user.getEmail())
                .id(UUID.randomUUID().toString())
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

    public String refreshAccessToken(String refreshToken) {
        Jwt jwt = jwtDecoder.decode(refreshToken);

        String email = jwt.getSubject();

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found"));

        return generateRefreshToken(user);
    }
}
