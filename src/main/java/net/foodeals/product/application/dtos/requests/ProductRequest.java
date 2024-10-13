package net.foodeals.product.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.foodeals.common.valueOjects.Price;
import net.foodeals.product.domain.enums.ProductType;

import java.util.UUID;

public record ProductRequest(
        @NotBlank String name,
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String barcode,
        @NotNull ProductType type,
        @NotNull Price price,
        @NotNull UUID categoryId
) {
}
