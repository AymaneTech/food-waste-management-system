package net.foodeals.authentication.application.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * AuthenticationResponse
 */
public record AuthenticationResponse(
        @JsonProperty("access_token")
        @NotBlank
        String accessToken,

        @JsonProperty("refresh_token")
        @NotBlank
        String refreshToken
) {
}
