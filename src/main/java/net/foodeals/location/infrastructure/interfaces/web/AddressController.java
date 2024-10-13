package net.foodeals.location.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.requests.AddressRequest;
import net.foodeals.location.application.dtos.responses.AddressResponse;
import net.foodeals.location.application.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        final List<AddressResponse> responses = service.findAll(pageNum, pageSize)
                .stream()
                .map(address -> mapper.map(address, AddressResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable("id") UUID id) {
        final AddressResponse response = mapper.map(service.findById(id), AddressResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest request) {
        final AddressResponse response = mapper.map(service.create(request), AddressResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable UUID id, @RequestBody @Valid AddressRequest request) {
        final AddressResponse response = mapper.map(service.update(id, request), AddressResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
