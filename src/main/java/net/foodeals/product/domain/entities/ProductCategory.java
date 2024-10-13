package net.foodeals.product.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.organizationEntity.domain.entities.Activity;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_categories")

@Getter
public class ProductCategory extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    private String slug;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Activity activity;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    ProductCategory() {
    }

    public ProductCategory(String name, String slug, Activity activity) {
        this.name = name;
        this.slug = slug;
        this.activity = activity;
    }

    public static ProductCategory create(String name, String slug, Activity activity) {
        return new ProductCategory(name, slug, activity);
    }

    public ProductCategory setName(String name) {
        this.name = name;
        return this;
    }

    public ProductCategory setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public ProductCategory setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public ProductCategory setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}
