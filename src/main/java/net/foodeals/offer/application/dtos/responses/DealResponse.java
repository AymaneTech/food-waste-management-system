package net.foodeals.offer.application.dtos.responses;

import net.foodeals.common.valueOjects.Price;
import net.foodeals.product.domain.entities.Product;

import java.util.UUID;

public record DealResponse(UUID id, Price price, Integer quantity, Product product) {
}
