package net.foodeals.user.application.services.impl;

import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.requests.RoleRequest;
import net.foodeals.user.application.services.RoleService;
import net.foodeals.user.application.services.FindAllAuthoritiesByIdsUseCase;
import net.foodeals.user.domain.entities.Authority;
import net.foodeals.user.domain.entities.Role;
import net.foodeals.user.domain.exceptions.AuthorityNotFoundException;
import net.foodeals.user.domain.exceptions.RoleNotFoundException;
import net.foodeals.user.domain.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final FindAllAuthoritiesByIdsUseCase findAllAuthoritiesByIdsUseCase;
    private final ModelMapper modelMapper;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Role> findAll(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public Role findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }

    @Override
    public Role create(RoleRequest request) {
        List<Authority> authorities = findAllAuthoritiesByIdsUseCase.execute(request.authorityIds());
        Role role = modelMapper.map(request, Role.class);
        role.setAuthorities(authorities);
        return repository.save(role);
    }

    @Override
    public Role update(UUID id, RoleRequest request) {
        List<Authority> authorities = findAllAuthoritiesByIdsUseCase.execute(request.authorityIds());
        Role role = findById(id);
        modelMapper.map(request, role);
        role.setAuthorities(authorities);
        return repository.save(role);
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id))
            throw new AuthorityNotFoundException(id);
        repository.softDelete(id);
    }
}
