package net.foodeals.location.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.requests.CityRequest;
import net.foodeals.location.application.dtos.responses.CityResponse;
import net.foodeals.location.application.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        final List<CityResponse> responses = service.findAll(pageNum, pageSize)
                .stream()
                .map(city -> mapper.map(city, CityResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getById(@PathVariable("id") UUID id) {
        final CityResponse response = mapper.map(service.findById(id), CityResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CityResponse> create(@RequestBody @Valid CityRequest request) {
        final CityResponse response = mapper.map(service.create(request), CityResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CityResponse> update(@PathVariable UUID id, @RequestBody @Valid CityRequest request) {
        final CityResponse response = mapper.map(service.update(id, request), CityResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
