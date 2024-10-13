package net.foodeals.organizationEntity.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Coordinates;
import net.foodeals.location.domain.entities.Address;
import net.foodeals.notification.domain.entity.Notification;
import net.foodeals.order.domain.entities.Coupon;
import net.foodeals.organizationEntity.domain.enums.EntityType;
import net.foodeals.user.domain.entities.User;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sub_entities")

@Getter
@Setter
public class SubEntity extends AbstractEntity<UUID> {

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private OrganizationEntity organizationEntity;

    @ManyToMany
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "subEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> user;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "subEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons;
}