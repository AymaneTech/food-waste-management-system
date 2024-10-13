package net.foodeals.product.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductCategoryRequest(@NotBlank String name, @NotNull UUID activityId) {
}
