package net.foodeals.location.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Coordinates;
import net.foodeals.order.domain.entities.Order;
import net.foodeals.organizationEntity.domain.entities.OrganizationEntity;
import net.foodeals.organizationEntity.domain.entities.SubEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "address")

@Getter
@Setter
public class Address extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String address;

    @Column(name = "extra_address")
    private String extraAddress;

    private String zip;

    @Embedded
    private Coordinates coordinates;

    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

    @OneToMany(mappedBy = "shippingAddress", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToOne(mappedBy = "address")
    private OrganizationEntity organizationEntity;

    @OneToOne(mappedBy = "address")
    private SubEntity subEntity;
}