package net.foodeals.contract.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Price;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subscription")

@Getter
@Setter
public class Subscription extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "amount_currency"))
    })
    private Price amount;

    private Date echeance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolutionContract> solutionContracts;
}