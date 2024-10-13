package net.foodeals.location.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.requests.StateRequest;
import net.foodeals.location.application.services.CountryService;
import net.foodeals.location.application.services.StateService;
import net.foodeals.location.domain.entities.Country;
import net.foodeals.location.domain.entities.State;
import net.foodeals.location.domain.exceptions.StateNotFoundException;
import net.foodeals.location.domain.repositories.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class StateServiceImpl implements StateService {
    private final StateRepository repository;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @Override
    public List<State> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<State> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public State findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    @Override
    public State create(StateRequest request) {
        Country country = countryService.findById(request.countryId());
        State state = modelMapper.map(request, State.class);
        state.setCountry(country);
        return repository.save(state);
    }

    @Override
    public State update(UUID id, StateRequest request) {
        Country country = countryService.findById(request.countryId());
        State existingState = findById(id);
        modelMapper.map(request, existingState);
        existingState.setCountry(country);
        return repository.save(existingState);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new StateNotFoundException(id);

        repository.softDelete(id);
    }
}
