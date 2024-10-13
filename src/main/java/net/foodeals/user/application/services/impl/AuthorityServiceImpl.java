package net.foodeals.user.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.requests.AuthorityRequest;
import net.foodeals.user.application.services.AuthorityService;
import net.foodeals.user.domain.entities.Authority;
import net.foodeals.user.domain.exceptions.AuthorityNotFoundException;
import net.foodeals.user.domain.repositories.AuthorityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<Authority> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Authority> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Authority findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthorityNotFoundException(id));
    }

    @Override
    public Authority create(AuthorityRequest request) {
        Authority authority = modelMapper.map(request, Authority.class);
        return repository.save(authority);
    }

    @Override
    public Authority update(UUID id, AuthorityRequest request) {
        Authority authority = findById(id);
        modelMapper.map(request, authority);
        return repository.save(authority);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new AuthorityNotFoundException(id);
        repository.softDelete(id);
    }
}
