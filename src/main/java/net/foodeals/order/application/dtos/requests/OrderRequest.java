package net.foodeals.order.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import net.foodeals.common.valueOjects.Price;
import net.foodeals.delivery.application.dtos.requests.delivery.DeliveryRequest;
import net.foodeals.location.application.dtos.requests.AddressRequest;
import net.foodeals.order.domain.enums.OrderStatus;
import net.foodeals.order.domain.enums.OrderType;

import java.util.UUID;

public record OrderRequest(
        @NotNull Price price,
        @NotNull OrderType type,
        @NotNull OrderStatus status,
        @NotNull Integer clientId,
        @NotNull UUID offerId,
        UUID couponId,
        AddressRequest shippingAddress,
        DeliveryRequest delivery
) {
}
