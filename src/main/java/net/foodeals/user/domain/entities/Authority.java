package net.foodeals.user.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Permission
 */
@Entity
@Table(name = "authorities")

@Getter
@Setter
public class Authority extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    private String value;

    @ManyToMany
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    public Authority() {

    }

    public Authority(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static Authority create(String name, String value) {
        return new Authority(name, value);
    }
}
