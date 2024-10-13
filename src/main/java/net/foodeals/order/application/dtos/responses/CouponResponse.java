package net.foodeals.order.application.dtos.responses;

import java.util.Date;
import java.util.UUID;

public record CouponResponse(
    UUID id,
    String code,
    Float discount,
    Date endsAt,
    Boolean isEnabled) {
}
