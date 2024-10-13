package net.foodeals.product.domain.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    private final UUID id;

    public ProductNotFoundException(UUID id) {
        super("product with id " + id + " not found");
        this.id = id;
    }
}
