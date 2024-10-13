package net.foodeals.delivery.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.delivery.domain.enums.DeliveryStatus;
import net.foodeals.order.domain.entities.Order;
import net.foodeals.user.domain.entities.User;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "deliveries")

@Getter
@Setter
public class Delivery extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User deliveryBoy;

    private Integer rating;

    @Enumerated
    private DeliveryStatus status;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryPosition> deliveryPositions;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    Delivery() {

    }

    public Delivery(User deliveryBoy, DeliveryStatus status) {
        this.deliveryBoy = deliveryBoy;
        this.status = status;
    }

    public static Delivery create(User deliveryBoy, DeliveryStatus status) {
        return new Delivery(deliveryBoy, status);
    }
}
