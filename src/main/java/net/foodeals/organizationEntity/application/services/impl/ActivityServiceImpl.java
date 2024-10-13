package net.foodeals.organizationEntity.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.organizationEntity.application.dtos.requests.ActivityRequest;
import net.foodeals.organizationEntity.application.services.ActivityService;
import net.foodeals.organizationEntity.domain.entities.Activity;
import net.foodeals.organizationEntity.domain.exceptions.ActivityNotFoundException;
import net.foodeals.organizationEntity.domain.repositories.ActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;

    @Override
    public List<Activity> findAll() {
        return List.of();
    }

    @Override
    public Page<Activity> findAll(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public Activity findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException(id));
    }

    @Override
    public Activity create(ActivityRequest dto) {
        return null;
    }

    @Override
    public Activity update(UUID id, ActivityRequest dto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
