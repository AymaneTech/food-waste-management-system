package net.foodeals.user.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.foodeals.user.domain.valueObjects.Name;

public record ClientRegisterRequest(
        @NotNull Name name,
        @NotBlank @Email String email,
        @NotBlank String phone,
        @NotBlank String password,
        @NotNull Boolean isEmailVerified) {
}
