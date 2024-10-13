package net.foodeals.authentication.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;

/**
 * LoginRequest
 */
public record LoginRequest(
        @NotBlank String email,
        @NotBlank String password) implements AuthRequest {
}
