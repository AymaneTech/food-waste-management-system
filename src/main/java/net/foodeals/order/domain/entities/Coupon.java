package net.foodeals.order.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.organizationEntity.domain.entities.SubEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "coupons")

@Getter
@Setter
public class Coupon extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String code;

    private Float discount;

    @ManyToOne
    private SubEntity subEntity;

    @Column(name = "ends_at")
    private Date endsAt;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @OneToMany(mappedBy = "coupon", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    Coupon() {

    }

    public void toggleIsEnabled() {
        isEnabled = !isEnabled;
    }
}
