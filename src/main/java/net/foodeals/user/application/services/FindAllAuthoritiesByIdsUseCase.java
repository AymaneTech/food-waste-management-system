package net.foodeals.user.application.services;


import net.foodeals.common.contracts.UseCase;
import net.foodeals.user.domain.entities.Authority;

import java.util.List;
import java.util.UUID;

public interface FindAllAuthoritiesByIdsUseCase extends UseCase<List<UUID>, List<Authority>> {
}
