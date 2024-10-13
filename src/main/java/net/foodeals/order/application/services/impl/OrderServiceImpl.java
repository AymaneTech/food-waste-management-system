package net.foodeals.order.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.delivery.application.services.DeliveryService;
import net.foodeals.location.application.services.AddressService;
import net.foodeals.offer.application.services.OfferService;
import net.foodeals.offer.domain.entities.Offer;
import net.foodeals.order.application.dtos.requests.OrderRequest;
import net.foodeals.order.application.services.CouponService;
import net.foodeals.order.application.services.OrderService;
import net.foodeals.order.domain.entities.Order;
import net.foodeals.order.domain.enums.OrderType;
import net.foodeals.order.domain.exceptions.OrderNotFoundException;
import net.foodeals.order.domain.repositories.OrderRepository;
import net.foodeals.user.application.services.UserService;
import net.foodeals.user.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final CouponService couponService;
    private final AddressService addressService;
    private final UserService userService;
    private final OfferService offerService;
    private final DeliveryService deliveryService;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Order> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Order findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order create(OrderRequest request) {
        final User client = userService.findById(request.clientId());
        final Offer offer = offerService.findById(request.offerId());

        final Order order = Order.create(request.price(), request.type(), request.status(), client, offer);

        if (request.type().equals(OrderType.DELIVERY)) {
            order
                    .setShippingAddress(addressService.create(request.shippingAddress()))
                    .setDelivery(deliveryService.create(request.delivery()));
        }

        if (request.couponId() != null) {
            order.setCoupon(
                    couponService.findById(request.couponId())
            );
        }

        return repository.save(order);
    }

    @Override
    public Order update(UUID id, OrderRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id))
            throw new OrderNotFoundException(id);

        repository.softDelete(id);
    }
}
