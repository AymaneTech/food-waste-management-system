package net.foodeals.location.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record CountryRequest(
        @NotBlank String name,
        @NotBlank String code
) {
}
