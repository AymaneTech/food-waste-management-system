package net.foodeals.location.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import net.foodeals.common.models.AbstractEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "countries")

@Getter
public class Country extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    private String code;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<State> states;

    Country() {
    }

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static Country create(String name, String code) {
        return new Country(name, code);
    }

    public Country setId(UUID id) {
        this.id = id;
        return this;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public Country setCode(String code) {
        this.code = code;
        return this;
    }

    public Country setStates(List<State> states) {
        this.states = states;
        return this;
    }
}