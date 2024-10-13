package net.foodeals.location.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.requests.CityRequest;
import net.foodeals.location.application.services.CityService;
import net.foodeals.location.application.services.StateService;
import net.foodeals.location.domain.entities.City;
import net.foodeals.location.domain.entities.State;
import net.foodeals.location.domain.exceptions.CityNotFoundException;
import net.foodeals.location.domain.repositories.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
class CityServiceImpl implements CityService {

    private final ModelMapper modelMapper;
    private final CityRepository repository;
    private final StateService stateService;


    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<City> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public City findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Override
    public City create(CityRequest request) {
        State state = stateService.findById(request.stateId());
        City city = modelMapper.map(request, City.class);
        city.setState(state);
        return repository.save(city);
    }

    @Override
    public City update(UUID id, CityRequest request) {
        State state = stateService.findById(request.stateId());
        City existingCity = repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));

        modelMapper.map(request, existingCity);
        existingCity.setState(state);
        return repository.save(existingCity);
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id))
            throw new CityNotFoundException(id);

        repository.softDelete(id);
    }
}
