package net.foodeals.delivery.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Coordinates;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "delivery_positions")

@Getter
@Setter
public class DeliveryPosition extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Embedded
    private Coordinates coordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    private Delivery delivery;
}
