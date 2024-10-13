package net.foodeals.product.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.offer.domain.entities.BoxItem;
import net.foodeals.offer.domain.entities.Deal;
import net.foodeals.product.domain.enums.ProductType;
import net.foodeals.common.valueOjects.Price;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")

@Getter
@Setter
public class Product extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    private String slug;

    private String description;

    private String title;

    private String barcode;

    @Column(name = "product_type")
    private ProductType type;

    @Embedded
    private Price price;

    @Column(name = "product_image_type")
    private String ProductImagePath;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deal> deals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoxItem> boxItems;
}
