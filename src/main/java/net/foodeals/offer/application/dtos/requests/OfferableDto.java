package net.foodeals.offer.application.dtos.requests;

import net.foodeals.offer.domain.enums.OfferType;

public record OfferableDto(OfferType type, BoxDto box, DealDto deal) {
}
