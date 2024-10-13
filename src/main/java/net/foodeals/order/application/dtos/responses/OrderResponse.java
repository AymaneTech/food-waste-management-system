package net.foodeals.order.application.dtos.responses;

import net.foodeals.common.valueOjects.Price;
import net.foodeals.location.application.dtos.responses.AddressResponse;
import net.foodeals.offer.application.dtos.responses.OfferResponse;
import net.foodeals.order.domain.enums.OrderStatus;
import net.foodeals.order.domain.enums.OrderType;
import net.foodeals.user.application.dtos.responses.UserResponse;

import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        Price price,
        OrderType type,
        OrderStatus status,
        UserResponse client,
        OfferResponse offer,
        AddressResponse shippingAddress,
        CouponResponse coupon,
        List<TransactionResponse> transactions

) {
}
 // todo: add delivery response here
