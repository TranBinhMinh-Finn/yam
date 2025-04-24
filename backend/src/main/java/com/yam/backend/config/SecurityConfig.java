package com.yam.backend.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.public-key.location}")
    private RSAPublicKey publicKey;

    @Value("${security.private-key.location}")
    private RSAPrivateKey privateKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register").permitAll()
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/refresh").hasRole("REFRESH")
                .anyRequest().authenticated()
        );

        http.oauth2ResourceServer(configure
                -> configure.jwt(Customizer.withDefaults()));

        return http.build();
    }


    public OAuth2TokenValidator<Jwt> tokenValidator() {
        final List<OAuth2TokenValidator<Jwt>> validators =
                List.of(new JwtTimestampValidator(),
                        new JwtIssuerValidator("yam.com"));
        return new DelegatingOAuth2TokenValidator<>(validators);
    }

    /**
     *
     * @return JwtDecoder object, with secret signed using RSA .
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        final NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withPublicKey(publicKey)
                .build();
        decoder.setJwtValidator(tokenValidator());
        return decoder;
    }

    /**
     *
     * @return JwtEncoder object, with secret signed using RSA .
     */

    @Bean
    public JwtEncoder jwtEncoder() {
        var jwk = new RSAKey
                .Builder(publicKey)
                .privateKey(privateKey)
                .build();

        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");

        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return converter;
    }

}
