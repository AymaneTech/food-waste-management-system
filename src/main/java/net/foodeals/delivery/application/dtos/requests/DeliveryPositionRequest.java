package net.foodeals.delivery.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import net.foodeals.common.valueOjects.Coordinates;

import java.util.UUID;

/**
 * DeliveryPositionRequest
 */
public record DeliveryPositionRequest(
        @NotNull Coordinates coordinates,
        @NotNull UUID deliveryId
) {
}
