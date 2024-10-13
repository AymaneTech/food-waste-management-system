package net.foodeals.offer.domain.exceptions;

import java.util.UUID;

public class OfferNotFoundException extends RuntimeException {
    private final UUID id;

    public OfferNotFoundException(UUID id) {
        super("Offer with id " + id + " not found");
        this.id = id;
    }
}
