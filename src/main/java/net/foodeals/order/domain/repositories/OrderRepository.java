package net.foodeals.order.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.order.domain.entities.Order;

import java.util.UUID;

public interface OrderRepository extends BaseRepository<Order, UUID> {
}
