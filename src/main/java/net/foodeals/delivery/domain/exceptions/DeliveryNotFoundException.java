package net.foodeals.delivery.domain.exceptions;

import java.util.UUID;

public class DeliveryNotFoundException extends RuntimeException {

    private final UUID id;

    public DeliveryNotFoundException(UUID id) {
        super("delviery with id " + id + " not found");
        this.id = id;
    }
}
