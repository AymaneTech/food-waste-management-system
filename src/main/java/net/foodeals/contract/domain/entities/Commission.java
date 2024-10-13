package net.foodeals.contract.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Price;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "commissions")

@Getter
@Setter
public class Commission extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "cash_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "cash_currency"))
    })
    private Price cash;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "card_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "card_currency"))
    })
    private Price card;

    @OneToOne(mappedBy = "commission", cascade = CascadeType.ALL)
    private SolutionContract solutionContracts;
}
