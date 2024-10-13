package net.foodeals.user.application.services.impl;

import lombok.RequiredArgsConstructor;
import net.foodeals.common.annotations.UseCase;
import net.foodeals.user.application.services.FindAllAuthoritiesByIdsUseCase;
import net.foodeals.user.domain.entities.Authority;
import net.foodeals.user.domain.repositories.AuthorityRepository;

import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
class FindAllAuthoritiesByIdsUsecaseImpl implements FindAllAuthoritiesByIdsUseCase {

    private final AuthorityRepository repository;

    @Override
    public List<Authority> execute(List<UUID> ids) {
        return repository.findAllById(ids);
    }
}
