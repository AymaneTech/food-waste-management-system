package net.foodeals.offer.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import net.foodeals.common.models.AbstractEntity;
import net.foodeals.common.valueOjects.Price;
import net.foodeals.offer.domain.enums.OfferType;
import net.foodeals.product.domain.entities.Product;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "deals")

@Getter
public class Deal extends AbstractEntity<UUID> implements IOfferChoice {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private Price price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;

    @Override
    public final OfferType getOfferType() {
        return OfferType.DEAL;
    }

    Deal () {

    }

    public Deal (Price price, Integer quantity, Product product) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    public static Deal create (Price price, Integer quantity, Product product) {
        return new Deal(price, quantity, product);
    }

    public Deal setPrice(Price price) {
        this.price = price;
        return this;
    }

    public Deal setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Deal setProduct(Product product) {
        this.product = product;
        return this;
    }
}
