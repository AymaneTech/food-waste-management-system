package net.foodeals.offer.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.requests.BoxDto;
import net.foodeals.offer.application.services.BoxItemService;
import net.foodeals.offer.application.services.BoxService;
import net.foodeals.offer.domain.entities.Box;
import net.foodeals.offer.domain.entities.BoxItem;
import net.foodeals.offer.domain.exceptions.BoxNotFoundException;
import net.foodeals.offer.domain.repositories.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class BoxServiceImpl implements BoxService {

    private final BoxRepository repository;
    private final BoxItemService boxItemsService;

    @Override
    public Box findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BoxNotFoundException(id));
    }

    @Override
    public Box create(BoxDto dto) {
        Box box = Box.create(dto.type(), new ArrayList<>());

        final List<BoxItem> items = boxItemsService.createAll(dto.items(), box);
        box.setBoxItems(items);

        return repository.save(box);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BoxNotFoundException(id);

        repository.softDelete(id);
    }
}
