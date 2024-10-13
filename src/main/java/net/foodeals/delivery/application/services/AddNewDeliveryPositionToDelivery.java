package net.foodeals.delivery.application.services;

import net.foodeals.common.contracts.UseCase;
import net.foodeals.delivery.application.dtos.requests.DeliveryPositionRequest;
import net.foodeals.delivery.domain.entities.DeliveryPosition;

public interface AddNewDeliveryPositionToDelivery extends UseCase<DeliveryPositionRequest, DeliveryPosition> {
}
    
