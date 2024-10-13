package net.foodeals.user.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.requests.AuthorityRequest;
import net.foodeals.user.application.dtos.responses.AuthorityResponse;
import net.foodeals.user.application.services.AuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/authorities")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AuthorityResponse>> getAll() {
        final List<AuthorityResponse> responses = service.findAll()
                .stream()
                .map(authority -> mapper.map(authority, AuthorityResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<AuthorityResponse>> getAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        final Page<AuthorityResponse> responses = service.findAll(pageNumber, pageSize)
                .map(authority -> mapper.map(authority, AuthorityResponse.class));
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorityResponse> getById(@PathVariable UUID id) {
        final AuthorityResponse response = mapper.map(service.findById(id), AuthorityResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AuthorityResponse> create(@RequestBody @Valid AuthorityRequest request) {
        final AuthorityResponse response = mapper.map(service.create(request), AuthorityResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorityResponse> update(@PathVariable UUID id, @RequestBody @Valid AuthorityRequest request) {
        final AuthorityResponse response = mapper.map(service.update(id, request), AuthorityResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
