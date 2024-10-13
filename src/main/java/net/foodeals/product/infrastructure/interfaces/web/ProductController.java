package net.foodeals.product.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.product.application.dtos.requests.ProductRequest;
import net.foodeals.product.application.dtos.responses.ProductResponse;
import net.foodeals.product.application.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        final Page<ProductResponse> response = service.findAll(pageNum, pageSize)
                .map(product -> mapper.map(product, ProductResponse.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        final ProductResponse response = mapper.map(
                service.findById(id),
                ProductResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
        final ProductResponse response = mapper.map(
                service.create(request),
                ProductResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable UUID id, @RequestBody @Valid ProductRequest request) {
        final ProductResponse response = mapper.map(
                service.update(id, request),
                ProductResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}