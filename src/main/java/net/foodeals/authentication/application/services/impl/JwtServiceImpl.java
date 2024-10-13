package net.foodeals.authentication.application.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import net.foodeals.authentication.application.dtos.responses.AuthenticationResponse;
import net.foodeals.authentication.application.services.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secretKey;

    @Getter
    private final Long expirationTime;
    private final Long refreshTokenExpirationTime;

    public JwtServiceImpl(
            @Value("${app.security.jwt.secret-key}") String secretKey,
            @Value("${app.security.jwt.expiration}") Long expirationTime,
            @Value("${app.security.jwt.refresh-token.expiration}") Long refreshTokenExpirationTime) {
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(final UserDetails userDetails, final Map<String, Object> extraClaims) {
        return buildToken(extraClaims, userDetails, expirationTime);
    }

    public String generateRefreshToken(final UserDetails userDetails, final Map<String, Object> extraClaims) {
        return buildToken(Map.of(), userDetails, refreshTokenExpirationTime);
    }

    public AuthenticationResponse generateTokens(final UserDetails userDetails, final Map<String, Object> extraClaims) {
        return new AuthenticationResponse(
                generateToken(userDetails, extraClaims),
                generateRefreshToken(userDetails, extraClaims)
        );
    }

    public Boolean isTokenValid(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(final String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String buildToken(final Map<String, Object> extraClaims, final UserDetails userDetails, final Long expirationTime) {
        final Map<String, Object> claims = new HashMap<>(extraClaims);
        claims.put("token_type", expirationTime.equals(this.expirationTime) ? "access" : "refresh");

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        final byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
