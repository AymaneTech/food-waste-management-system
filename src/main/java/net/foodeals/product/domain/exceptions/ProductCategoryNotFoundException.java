package net.foodeals.product.domain.exceptions;

import java.util.UUID;

public class ProductCategoryNotFoundException extends RuntimeException {
    private final UUID id;

    public ProductCategoryNotFoundException(UUID id) {
        super("product category with id " + id + " not found");
        this.id = id;
    }
}
