package net.foodeals.user.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.foodeals.common.models.AbstractEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Role
 */
@Entity
@Table(name = "roles")

@Getter
@Setter
@ToString
public class Role extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(UUID id, String name, List<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    public static Role create(UUID id, String name, List<Authority> authorities) {
        return new Role(id, name, authorities);
    }
}
