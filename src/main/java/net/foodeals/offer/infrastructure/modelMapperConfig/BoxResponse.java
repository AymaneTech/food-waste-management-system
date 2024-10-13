package net.foodeals.offer.infrastructure.modelMapperConfig;

import net.foodeals.offer.application.dtos.responses.BoxItemResponse;

import java.util.List;
import java.util.UUID;

public record BoxResponse(
        UUID id,
        List<BoxItemResponse> itmes
) {
}
