package net.foodeals.organizationEntity.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Coordinates;
import net.foodeals.location.domain.entities.Address;
import net.foodeals.notification.domain.entity.Notification;
import net.foodeals.organizationEntity.domain.enums.EntityType;
import net.foodeals.user.domain.entities.User;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "organization_entities")

@Getter
@Setter
public class OrganizationEntity extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "cover_path")
    private String coverPath;

    @Embedded
    private Coordinates coordinates;

    @Enumerated
    private EntityType type;

    @OneToMany(mappedBy = "organizationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubEntity> subEntities = new ArrayList<>();

    @ManyToMany
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "organizationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();


    @ManyToMany
    private List<Solution> solutions = new ArrayList<>();

    @OneToMany(mappedBy = "organizationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @OneToOne
    private Address address;
}