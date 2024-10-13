package net.foodeals.delivery.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.delivery.application.dtos.requests.delivery.DeliveryRequest;
import net.foodeals.delivery.application.services.DeliveryService;
import net.foodeals.delivery.domain.entities.Delivery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService service;

    @GetMapping
    public ResponseEntity<List<Delivery>> findAll() {
        final List<Delivery> deliveries = service.findAll();
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("{id}")
    public ResponseEntity<Delivery> findById(@PathVariable UUID id) {
        final Delivery delivery = service.findById(id);
        return ResponseEntity.ok(delivery);
    }

    @PostMapping
    public ResponseEntity<Delivery> create(@RequestBody @Valid DeliveryRequest request) {
        final Delivery delivery = service.create(request);
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Delivery> update(@PathVariable UUID id, @RequestBody @Valid DeliveryRequest request) {
        final Delivery delivery = service.update(id, request);
        return ResponseEntity.ok(delivery);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
