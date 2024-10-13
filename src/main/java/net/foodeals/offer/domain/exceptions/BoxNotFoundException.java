package net.foodeals.offer.domain.exceptions;

import java.util.UUID;

public class BoxNotFoundException extends RuntimeException {
    private final UUID id;

    public BoxNotFoundException(UUID id) {
        super("Box with id" + id + " not found");
        this.id = id;
    }
}