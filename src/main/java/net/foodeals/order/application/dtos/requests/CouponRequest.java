package net.foodeals.order.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record CouponRequest(
        @NotBlank String code,
        @NotNull Float discount,
        @NotNull Date endsAt,
        @NotNull UUID subEntityId,
        @NotNull Boolean isEnabled) {
}
