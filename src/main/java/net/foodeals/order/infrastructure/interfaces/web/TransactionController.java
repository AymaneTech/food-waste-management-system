package net.foodeals.order.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.order.application.dtos.requests.TransactionRequest;
import net.foodeals.order.application.dtos.responses.TransactionResponse;
import net.foodeals.order.application.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> getAll(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        final Page<TransactionResponse> response = service.findAll(pageNum, pageSize)
                .map(transaction -> mapper.map(transaction, TransactionResponse.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable UUID id) {
        final TransactionResponse response = mapper.map(
                service.findById(id),
                TransactionResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody @Valid TransactionRequest request) {
        final TransactionResponse response = mapper.map(
                service.create(request),
                TransactionResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable UUID id, @RequestBody @Valid TransactionRequest request) {
        final TransactionResponse response = mapper.map(
                service.update(id, request),
                TransactionResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
