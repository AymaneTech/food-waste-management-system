package net.foodeals.user.application.dtos.responses;

import java.util.List;
import java.util.UUID;

public record RoleResponse(
        UUID id,
        String name,
        List<AuthorityResponse> authorities
) {
}
