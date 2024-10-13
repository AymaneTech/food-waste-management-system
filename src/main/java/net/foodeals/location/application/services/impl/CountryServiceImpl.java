package net.foodeals.location.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.requests.CountryRequest;
import net.foodeals.location.application.services.CountryService;
import net.foodeals.location.domain.entities.Country;
import net.foodeals.location.domain.exceptions.CountryNotFoundException;
import net.foodeals.location.domain.repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Country> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Country findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country create(CountryRequest request) {
        Country country = modelMapper.map(request, Country.class);
        return repository.save(country);
    }

    @Override
    public Country update(UUID id, CountryRequest request) {
        Country existingCountry = repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        modelMapper.map(request, existingCountry);
        return repository.save(existingCountry);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new CountryNotFoundException(id);

        repository.softDelete(id);
    }
}

