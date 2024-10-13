package net.foodeals.delivery.application.services;

import java.util.UUID;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.delivery.application.dtos.requests.delivery.DeliveryRequest;
import net.foodeals.delivery.domain.entities.Delivery;

/**
 * DeliveryService
 */
public interface DeliveryService extends CrudService<Delivery, UUID, DeliveryRequest> {

}
