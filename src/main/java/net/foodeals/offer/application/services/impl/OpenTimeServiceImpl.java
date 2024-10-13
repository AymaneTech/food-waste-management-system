package net.foodeals.offer.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.requests.OpenTimeDto;
import net.foodeals.offer.application.services.OpenTimeService;
import net.foodeals.offer.domain.entities.Offer;
import net.foodeals.offer.domain.entities.OpenTime;
import net.foodeals.offer.domain.repositories.OpenTimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class OpenTimeServiceImpl implements OpenTimeService {

    private final OpenTimeRepository repository;
    private final ModelMapper mapper;

    public OpenTime create(OpenTimeDto dto, Offer offer) {
        OpenTime openTime = mapper.map(dto, OpenTime.class);
        openTime.setOffer(offer);
        return repository.save(openTime);
    }
}
