package net.foodeals.order.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Price;
import net.foodeals.delivery.domain.entities.Delivery;
import net.foodeals.location.domain.entities.Address;
import net.foodeals.offer.domain.entities.Offer;
import net.foodeals.order.domain.enums.OrderStatus;
import net.foodeals.order.domain.enums.OrderType;
import net.foodeals.user.domain.entities.User;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")

@Getter
public class Order extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Embedded
    private Price price;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType type;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    private User client;

    @ManyToOne(cascade = CascadeType.ALL)
    private Offer offer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    private Delivery delivery;

    @ManyToOne(cascade = CascadeType.ALL)
    private Coupon coupon;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    Order() {

    }

    public Order(Price price, OrderType type, OrderStatus status, User client, Offer offer) {
        this.price = price;
        this.type = type;
        this.status = status;
        this.client = client;
        this.offer = offer;
    }

    public static Order create(Price price, OrderType type, OrderStatus status, User client, Offer offer) {
        return new Order(price, type, status, client, offer);
    }

    public Order setPrice(Price price) {
        this.price = price;
        return this;
    }

    public Order setOrderType(OrderType orderType) {
        this.type = orderType;
        return this;
    }

    public Order setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
        return this;
    }

    public Order setShippingAddress(Address address) {
        this.shippingAddress = address;
        return this;
    }

    public Order setClient(User client) {
        this.client = client;
        return this;
    }

    public Order setOffer(Offer offer) {
        this.offer = offer;
        return this;
    }

    public Order setDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public Order setCoupon(Coupon coupon) {
        this.coupon = coupon;
        return this;
    }

    public Order setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }
}
