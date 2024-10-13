package net.foodeals.product.application.dtos.responses;

import net.foodeals.common.valueOjects.Price;
import net.foodeals.product.domain.enums.ProductType;

import java.util.UUID;

public record ProductResponse(
        UUID id, String name, String slug, String title,
        String description, String imageUrl, ProductCategoryResponse category,
        String barcode, ProductType type, Price price) {
}
