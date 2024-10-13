package net.foodeals.contract.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.user.domain.entities.User;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
public class UserContract extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false )
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

}
