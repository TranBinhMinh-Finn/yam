package com.yam.backend.service;

import com.yam.backend.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    @Value("${security.access-key-expire-time}")
    int accessKeyExpireTime;

    @Value("${security.refresh-key-expire-time}")
    int refreshKeyExpireTime;

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
                .issuer("yam.com")
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("scope", user.getRole())
                .id(UUID.randomUUID().toString())
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

}
