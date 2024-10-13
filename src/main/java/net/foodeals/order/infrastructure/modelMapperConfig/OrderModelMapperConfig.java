package net.foodeals.order.infrastructure.modelMapperConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.responses.AddressResponse;
import net.foodeals.offer.application.dtos.responses.OfferResponse;
import net.foodeals.order.application.dtos.responses.CouponResponse;
import net.foodeals.order.application.dtos.responses.OrderResponse;
import net.foodeals.order.application.dtos.responses.TransactionResponse;
import net.foodeals.order.domain.entities.Coupon;
import net.foodeals.order.domain.entities.Order;
import net.foodeals.order.domain.entities.Transaction;
import net.foodeals.user.application.dtos.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OrderModelMapperConfig {

    private final ModelMapper mapper;

    @PostConstruct
    public void configure() {
        mapper.addConverter(context -> {
            Coupon coupon = context.getSource();
            return new CouponResponse(
                    coupon.getId(),
                    coupon.getCode(),
                    coupon.getDiscount(),
                    coupon.getEndsAt(),
                    coupon.getIsEnabled()
            );
        }, Coupon.class, CouponResponse.class);

        mapper.addConverter(context -> {
            Order order = context.getSource();
            return new OrderResponse(
                    order.getId(),
                    order.getPrice(),
                    order.getType(),
                    order.getStatus(),
                    mapper.map(order.getClient(), UserResponse.class),
                    mapper.map(order.getOffer(), OfferResponse.class),
                    mapper.map(order.getShippingAddress(), AddressResponse.class),
                    mapper.map(order.getCoupon(), CouponResponse.class),
                    order.getTransactions().stream()
                            .map(transaction -> mapper.map(transaction, TransactionResponse.class))
                            .collect(Collectors.toList())
            );
        }, Order.class, OrderResponse.class);

        mapper.addConverter(context -> {
            Transaction transaction = context.getSource();
            return new TransactionResponse(
                    transaction.getId(),
                    transaction.getPaymentId(),
                    transaction.getReference(),
                    transaction.getContext(),
                    transaction.getPrice(),
                    transaction.getStatus(),
                    transaction.getType(),
                    mapper.map(transaction.getOrder(), OrderResponse.class)
            );
        }, Transaction.class, TransactionResponse.class);
    }
}