package net.foodeals.authentication.application.services;

import net.foodeals.authentication.application.dtos.requests.LoginRequest;
import net.foodeals.authentication.application.dtos.requests.RegisterRequest;
import net.foodeals.authentication.application.dtos.responses.AuthenticationResponse;

/**
 * AuthenticationService
 */
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);
}
