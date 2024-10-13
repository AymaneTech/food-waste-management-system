package net.foodeals.user.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.requests.RoleRequest;
import net.foodeals.user.application.dtos.responses.RoleResponse;
import net.foodeals.user.application.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAll() {
        final List<RoleResponse> responses = service.findAll()
                .stream()
                .map(role -> mapper.map(role, RoleResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<RoleResponse>> getAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        final Page<RoleResponse> responses = service.findAll(pageNumber, pageSize)
                .map(role -> mapper.map(role, RoleResponse.class));
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getById(@PathVariable UUID id) {
        final RoleResponse response = mapper.map(service.findById(id), RoleResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponse> getByName(@PathVariable String name) {
        final RoleResponse response = mapper.map(service.findByName(name), RoleResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        final RoleResponse response = mapper.map(service.create(request), RoleResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoleResponse> update(@PathVariable UUID id, @RequestBody @Valid RoleRequest request) {
        final RoleResponse response = mapper.map(service.update(id, request), RoleResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}