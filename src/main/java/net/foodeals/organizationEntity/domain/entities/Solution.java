package net.foodeals.organizationEntity.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solutions")

@Getter
@Setter
public class Solution extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "solutions")
    private List<OrganizationEntity> organizationEntities = new ArrayList<>();

}
