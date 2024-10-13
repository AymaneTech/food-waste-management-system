package net.foodeals.user.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.user.domain.valueObjects.AuthenticationTokenDetails;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

/**
 * Account
 */
@Entity
@Table(name = "accounts")

@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String type;

    private String provider;

    @Column(name = "provider_account_id")
    private String providerAccountId;

    @Embedded
    private AuthenticationTokenDetails tokenDetails;

    private String scope;

    @Column(name = "id_token")
    private String idToken;

    @Column(name = "session_state")
    private String sessionState;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
