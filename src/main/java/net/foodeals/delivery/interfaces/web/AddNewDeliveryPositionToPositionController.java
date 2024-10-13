package net.foodeals.delivery.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.delivery.application.dtos.requests.DeliveryPositionRequest;
import net.foodeals.delivery.application.services.AddNewDeliveryPositionToDelivery;
import net.foodeals.delivery.domain.entities.DeliveryPosition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/deliveries")
@RequiredArgsConstructor
public class AddNewDeliveryPositionToPositionController {
    private final AddNewDeliveryPositionToDelivery addNewDeliveryPositionToDelivery;

    @PostMapping("/{deliveryId}/positions")
    public ResponseEntity<DeliveryPosition> addNewDeliveryPositionToPosition(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryPositionRequest request) {
        final DeliveryPosition deliveryPosition = addNewDeliveryPositionToDelivery.execute(request);
        return new ResponseEntity<>(deliveryPosition, HttpStatus.CREATED);
    }
}
