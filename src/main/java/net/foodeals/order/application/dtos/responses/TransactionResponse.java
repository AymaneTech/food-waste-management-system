package net.foodeals.order.application.dtos.responses;

import net.foodeals.common.valueOjects.Price;
import net.foodeals.order.domain.enums.TransactionStatus;
import net.foodeals.order.domain.enums.TransactionType;

import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String paymentId,
        String reference,
        String context,
        Price price,
        TransactionStatus status,
        TransactionType type,
        OrderResponse order) {
}
