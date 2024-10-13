package net.foodeals.common.valueOjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinates(Float latitude, Float longitude) {
}
