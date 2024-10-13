package net.foodeals.user.application.dtos.responses;

import java.util.UUID;

public record AuthorityResponse(
        UUID id,
        String name,
        String value
) {
}
