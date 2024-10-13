package net.foodeals.offer.application.dtos.responses;

import net.foodeals.common.valueOjects.Price;
import net.foodeals.product.domain.entities.Product;

public record BoxItemResponse(
        Price price,
        Integer quantity,
        Product product
) {
}
