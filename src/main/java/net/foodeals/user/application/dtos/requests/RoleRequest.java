package net.foodeals.user.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RoleRequest(
        @NotBlank String name,
        @NotNull List<UUID> authorityIds
) {
}
