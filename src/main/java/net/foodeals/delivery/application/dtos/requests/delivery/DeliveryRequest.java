package net.foodeals.delivery.application.dtos.requests.delivery;

import jakarta.validation.constraints.NotNull;
import net.foodeals.delivery.domain.enums.DeliveryStatus;

import java.util.List;
import java.util.UUID;

/**
 * DeliveryRequest
 */
public record DeliveryRequest(
        @NotNull Integer deliveryBoyId,
        @NotNull DeliveryStatus status,
        @NotNull List<UUID> orderIds,
        @NotNull DeliveryPositionRequest initialPosition) {
}
