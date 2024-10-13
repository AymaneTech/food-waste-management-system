package net.foodeals.authentication.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.authentication.application.dtos.requests.LoginRequest;
import net.foodeals.authentication.application.dtos.requests.RegisterRequest;
import net.foodeals.authentication.application.dtos.responses.AuthenticationResponse;
import net.foodeals.authentication.application.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(
                service.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(
                service.login(request));
    }
}
