package net.foodeals.user.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.user.domain.entities.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends BaseRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
