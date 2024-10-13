package net.foodeals.product.application.dtos.responses;

import net.foodeals.organizationEntity.application.dtos.responses.ActivityResponse;

import java.util.UUID;

public record ProductCategoryResponse(
        UUID id,
        String name,
        String slug,
        ActivityResponse activity
) {
}
