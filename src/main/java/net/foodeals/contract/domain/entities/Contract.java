package net.foodeals.contract.domain.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.user.domain.entities.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contracts")

@Getter
@Setter
public class Contract extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    private String content;


    @OneToOne(mappedBy = "contract")
    private UserContract userContracts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolutionContract> solutionContracts;
}
