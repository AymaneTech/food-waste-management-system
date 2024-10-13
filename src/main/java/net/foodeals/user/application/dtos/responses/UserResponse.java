package net.foodeals.user.application.dtos.responses;

import net.foodeals.user.domain.valueObjects.Name;

public record UserResponse(
        Integer id,
        Name name,
        String email,
        String phone,
        RoleResponse role
) {
}
