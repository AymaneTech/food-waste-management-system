package net.foodeals.order.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.order.application.dtos.requests.OrderRequest;
import net.foodeals.order.domain.entities.Order;

import java.util.UUID;

public interface OrderService extends CrudService<Order, UUID, OrderRequest> {
}
