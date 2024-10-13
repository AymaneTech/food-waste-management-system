package net.foodeals.delivery.application.dtos.requests.delivery;

import jakarta.validation.constraints.NotNull;

import net.foodeals.common.valueOjects.Coordinates;

/**
 * DeliveryPositionRequest
 */
public record DeliveryPositionRequest(
        @NotNull Coordinates coordinates) {
}
