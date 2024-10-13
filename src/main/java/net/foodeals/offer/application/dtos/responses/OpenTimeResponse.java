package net.foodeals.offer.application.dtos.responses;

import java.util.UUID;

public record OpenTimeResponse(
        UUID id,
        String day,
        String from,
        String to
) {}
