package net.foodeals.order.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.order.application.dtos.requests.OrderRequest;
import net.foodeals.order.application.dtos.responses.OrderResponse;
import net.foodeals.order.application.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAll(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        final Page<OrderResponse> response = service.findAll(pageNum, pageSize)
                .map(order -> mapper.map(order, OrderResponse.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable UUID id) {
        final OrderResponse response = mapper.map(
                service.findById(id),
                OrderResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderRequest request) {
        final OrderResponse response = mapper.map(
                service.create(request),
                OrderResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable UUID id, @RequestBody @Valid OrderRequest request) {
        final OrderResponse response = mapper.map(
                service.update(id, request),
                OrderResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
