package net.foodeals.order.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Price;
import net.foodeals.order.domain.enums.TransactionStatus;
import net.foodeals.order.domain.enums.TransactionType;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "transactions")

@Getter
@Setter
public class Transaction extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "payment_id")
    private String paymentId;

    private String reference;

    private String context;

    @Embedded
    private Price price;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;
}
