package net.foodeals.order.infrastructure.interfaces.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.foodeals.order.application.dtos.requests.CouponRequest;
import net.foodeals.order.application.dtos.responses.CouponResponse;
import net.foodeals.order.application.services.CouponService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<CouponResponse>> getAll(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        final Page<CouponResponse> response = service.findAll(pageNum, pageSize)
                .map(coupon -> mapper.map(coupon, CouponResponse.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> getById(@PathVariable UUID id) {
        final CouponResponse response = mapper.map(
                service.findById(id),
                CouponResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CouponResponse> create(@RequestBody @Valid CouponRequest request) {
        final CouponResponse response = mapper.map(
                service.create(request),
                CouponResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CouponResponse> update(@PathVariable UUID id, @RequestBody @Valid CouponRequest request) {
        final CouponResponse response = mapper.map(
                service.update(id, request),
                CouponResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<CouponResponse> toggleIsEnabled(@PathVariable UUID id) {
        final CouponResponse response = mapper.map(
                service.toggleIsEnabled(id),
                CouponResponse.class);
        return ResponseEntity.ok(response);
    }
}