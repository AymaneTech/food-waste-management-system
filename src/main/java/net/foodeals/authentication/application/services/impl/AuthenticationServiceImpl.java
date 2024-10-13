package net.foodeals.authentication.application.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.foodeals.authentication.application.dtos.requests.LoginRequest;
import net.foodeals.authentication.application.dtos.requests.RegisterRequest;
import net.foodeals.authentication.application.dtos.responses.AuthenticationResponse;
import net.foodeals.authentication.application.services.AuthenticationService;
import net.foodeals.authentication.application.services.JwtService;
import net.foodeals.user.application.dtos.requests.UserRequest;
import net.foodeals.user.application.services.UserService;
import net.foodeals.user.domain.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * AuthenticationServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        final User user = userService.create(new UserRequest(request.name(), request.email(), request.phone(), request.password(), request.isEmailVerified(), request.roleId()));
        return handleRegistration(user);
    }

    public AuthenticationResponse login(LoginRequest request) {
        System.out.println("login attempt for user: " + request.email());
        try {

            System.out.println("------------------- the user found by email -------------------");
            System.out.println("user " + userService.loadUserByUsername(request.email()));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()));

            log.info("Authentication successful for user: {}", request.email());

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            System.out.println("the authentication" + authentication.getPrincipal());

            log.debug("SecurityContext updated with authentication");

            final User user = userService.loadUserByUsername(request.email());
            System.out.println(user);
            log.debug("User loaded: {}", user);
            log.debug("User authorities: {}", user.getAuthorities());

            AuthenticationResponse response = getTokens(user);
            log.debug("Tokens generated for user: {}", user.getEmail());

            return response;
        } catch (RuntimeException e) {
            log.error("Login failed for user: {}", request.email());
            System.out.println("message from exception " + e.getMessage());
            throw e;
        }
    }

    private AuthenticationResponse handleRegistration(User user) {
        return getTokens(user);
    }

    private AuthenticationResponse handleLogin(LoginRequest request) {
        System.out.println("here is the login request " + request);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        final User user = userService.loadUserByUsername(request.email());
        return getTokens(user);
    }

    private AuthenticationResponse getTokens(User user) {
        final Map<String, Object> extraClaims = Map.of(
                "email", user.getEmail(),
                "phone", user.getPhone(),
                "role", user.getRole().getName()
        );
        return jwtService.generateTokens(user, extraClaims);
    }
}