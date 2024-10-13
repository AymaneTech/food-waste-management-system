package net.foodeals.delivery.domain.repositories;

import java.util.UUID;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.delivery.domain.entities.Delivery;

public interface DeliveryRepository extends BaseRepository<Delivery, UUID> {
}
