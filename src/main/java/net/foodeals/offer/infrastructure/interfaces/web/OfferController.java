package net.foodeals.offer.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.requests.OfferRequest;
import net.foodeals.offer.application.dtos.responses.OfferResponse;
import net.foodeals.offer.application.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<OfferResponse>> getAll(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        final Page<OfferResponse> response = service.findAll(pageNum, pageSize)
                .map(offer -> mapper.map(offer, OfferResponse.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> getById(@PathVariable UUID id) {
        final OfferResponse response = mapper.map(
                service.findById(id),
                OfferResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OfferResponse> create(@RequestBody @Valid OfferRequest request) {
        final OfferResponse response = mapper.map(
                service.create(request),
                OfferResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OfferResponse> update(@PathVariable UUID id, @RequestBody @Valid OfferRequest request) {
        final OfferResponse response = mapper.map(
                service.update(id, request),
                OfferResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
