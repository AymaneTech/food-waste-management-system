package net.foodeals.offer.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.foodeals.common.models.AbstractEntity;

import java.util.UUID;

@Entity
@Table

@Getter
@Setter
public class OpenTime extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    private String day;

    @Column(name = "start_time")
    private String from;

    @Column(name = "end_time")
    private String to;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;
}
