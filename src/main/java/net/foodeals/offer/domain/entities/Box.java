package net.foodeals.offer.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.offer.domain.enums.BoxType;
import net.foodeals.offer.domain.enums.OfferType;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "boxes")

@Getter
@Setter
public class Box extends AbstractEntity<UUID> implements IOfferChoice {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    private BoxType type;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BoxItem> boxItems;

    Box() {
    }

    public Box(BoxType type, List<BoxItem> boxItems) {
        this.type = type;
        this.boxItems = boxItems;
    }

    public static Box create(BoxType type, List<BoxItem> boxItems) {
        return new Box(
                type,
                boxItems
        );
    }

    @Override
    public OfferType getOfferType() {
        return OfferType.BOX;
    }

}
