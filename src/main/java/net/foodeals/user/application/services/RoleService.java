package net.foodeals.user.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.user.application.dtos.requests.RoleRequest;
import net.foodeals.user.domain.entities.Role;

import java.util.UUID;

public interface RoleService extends CrudService<Role, UUID, RoleRequest> {
    Role findByName(String name);
}
