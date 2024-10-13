package net.foodeals.user.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.requests.UserRequest;
import net.foodeals.user.application.dtos.responses.UserResponse;
import net.foodeals.user.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        final List<UserResponse> responses = service.findAll()
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<UserResponse>> getAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        final Page<UserResponse> responses = service.findAll(pageNumber, pageSize)
                .map(user -> mapper.map(user, UserResponse.class));
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Integer id) {
        final UserResponse response = mapper.map(service.findById(id), UserResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable String email) {
        final UserResponse response = mapper.map(service.findByEmail(email), UserResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        final UserResponse response = mapper.map(service.create(request), UserResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Integer id, @RequestBody @Valid UserRequest request) {
        final UserResponse response = mapper.map(service.update(id, request), UserResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}