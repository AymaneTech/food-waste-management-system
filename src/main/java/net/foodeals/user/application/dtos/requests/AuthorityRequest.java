package net.foodeals.user.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record AuthorityRequest(
        @NotBlank String name,
        @NotBlank String value
) {
}
