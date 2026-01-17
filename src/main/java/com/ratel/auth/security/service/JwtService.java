package com.ratel.auth.security.service;

import com.ratel.auth.exception.BusinessException;
import com.ratel.auth.exception.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private static final String TOKEN_TYPE = "token_type";

    public String generateAccessToken(@NotNull String username) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("ratel_auth")
                .issuedAt(now)
                .expiresAt(now.plus(accessTokenExpiration, ChronoUnit.HOURS))
                .subject(username)
                .claim(TOKEN_TYPE, "ACCESS_TOKEN")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(@NotNull String username) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("ratel_auth")
                .issuedAt(now)
                .expiresAt(now.plus(refreshTokenExpiration, ChronoUnit.HOURS))
                .subject(username)
                .claim(TOKEN_TYPE, "REFRESH_TOKEN")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean isTokenValid(@NotNull String token, @NotNull String expectedUsername) {
        String username = extractUsername(token);
        if (username == null)
            throw new BusinessException(ErrorCode.TOKEN_IS_INVALID, token);
        return username.equals(expectedUsername) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(@NotNull String token) {
        Instant expiresAt = jwtDecoder.decode(token).getExpiresAt();
        if (expiresAt == null)
            throw new BusinessException(ErrorCode.TOKEN_IS_INVALID, token);
        return expiresAt.isBefore(Instant.now());
    }

    public String extractUsername(@NotNull String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    public String refreshAccessToken(@NotNull String refreshToken) {
        if (!"REFRESH_TOKEN".equals(jwtDecoder.decode(refreshToken).getClaimAsString(TOKEN_TYPE))) {
            throw  new BusinessException(ErrorCode.TOKEN_IS_INVALID, refreshToken);
        }
        String username = jwtDecoder.decode(refreshToken).getSubject();
        return generateAccessToken(username);
    }

}
