package net.foodeals.user.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.user.domain.entities.Authority;

import java.util.UUID;

public interface AuthorityRepository extends BaseRepository<Authority, UUID> {
}
