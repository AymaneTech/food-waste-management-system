package net.foodeals.authentication.application.services;

import net.foodeals.authentication.application.dtos.responses.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(final String token);

    String generateToken(final UserDetails userDetails, final Map<String, Object> extraClaims);

    String generateRefreshToken(final UserDetails userDetails, final Map<String, Object> extraClaims);

    AuthenticationResponse generateTokens(final UserDetails userDetails, final Map<String, Object> extraClaims);

    Long getExpirationTime();

    Boolean isTokenValid(final String token, final UserDetails userDetails);
}
