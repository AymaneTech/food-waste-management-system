package net.foodeals.delivery.application.services.impl;

import lombok.RequiredArgsConstructor;
import net.foodeals.common.annotations.UseCase;
import net.foodeals.delivery.application.dtos.requests.DeliveryPositionRequest;
import net.foodeals.delivery.application.services.AddNewDeliveryPositionToDelivery;
import net.foodeals.delivery.domain.entities.Delivery;
import net.foodeals.delivery.domain.entities.DeliveryPosition;
import net.foodeals.delivery.domain.exceptions.DeliveryNotFoundException;
import net.foodeals.delivery.domain.repositories.DeliveryPositionRepository;
import net.foodeals.delivery.domain.repositories.DeliveryRepository;
import org.modelmapper.ModelMapper;

/**
 * AddNewDeliveryPositionToDeliveryUsecaseImpl
 */
@UseCase
@RequiredArgsConstructor
class AddNewDeliveryPositionToDeliveryImpl implements AddNewDeliveryPositionToDelivery {

    private final DeliveryPositionRepository repository;
    private final DeliveryRepository deliveryRepository;
    private final ModelMapper mapper;

    @Override
    public DeliveryPosition execute(DeliveryPositionRequest request) {
        final Delivery delivery = deliveryRepository.findById(request.deliveryId())
                .orElseThrow(() -> new DeliveryNotFoundException(request.deliveryId()));
        final DeliveryPosition deliveryPosition = mapper.map(request, DeliveryPosition.class);
        deliveryPosition.setDelivery(delivery);
        return repository.save(deliveryPosition);
    }
}
