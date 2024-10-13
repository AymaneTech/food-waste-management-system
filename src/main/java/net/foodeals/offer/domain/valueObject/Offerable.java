package net.foodeals.offer.domain.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import net.foodeals.offer.domain.enums.OfferType;

import java.util.UUID;

@Embeddable
public record Offerable(
        @Column(name = "offerable_id")
        UUID id,

        @Enumerated(EnumType.STRING)
        @Column(name = "offerable_type")
        OfferType type){
}
