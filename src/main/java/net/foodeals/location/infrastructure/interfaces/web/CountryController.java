package net.foodeals.location.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.requests.CountryRequest;
import net.foodeals.location.application.dtos.responses.CountryResponse;
import net.foodeals.location.application.services.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        final List<CountryResponse> responses = service.findAll(pageNum, pageSize)
                .stream()
                .map(country -> mapper.map(country, CountryResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getById(@PathVariable("id") UUID id) {
        final CountryResponse response = mapper.map(service.findById(id), CountryResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CountryResponse> create(@RequestBody @Valid CountryRequest request) {
        final CountryResponse response = mapper.map(service.create(request), CountryResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CountryResponse> update(@PathVariable UUID id, @RequestBody @Valid CountryRequest request) {
        final CountryResponse response = mapper.map(service.update(id, request), CountryResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
