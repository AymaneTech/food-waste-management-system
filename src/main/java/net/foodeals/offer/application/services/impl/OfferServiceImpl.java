package net.foodeals.offer.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.requests.OfferRequest;
import net.foodeals.offer.application.services.BoxService;
import net.foodeals.offer.application.services.DealService;
import net.foodeals.offer.application.services.OfferService;
import net.foodeals.offer.application.services.OpenTimeService;
import net.foodeals.offer.domain.entities.IOfferChoice;
import net.foodeals.offer.domain.entities.Offer;
import net.foodeals.offer.domain.entities.OpenTime;
import net.foodeals.offer.domain.enums.OfferType;
import net.foodeals.offer.domain.exceptions.OfferNotFoundException;
import net.foodeals.offer.domain.repositories.OfferRepository;
import net.foodeals.offer.domain.valueObject.Offerable;
import net.foodeals.organizationEntity.application.services.ActivityService;
import net.foodeals.organizationEntity.domain.entities.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OpenTimeService openTimeService;
    private final ActivityService activityService;

    private final BoxService boxService;
    private final DealService dealService;

    private final ModelMapper modelMapper;

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Page<Offer> findAll(Integer pageNumber, Integer pageSize) {
        return offerRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Offer findById(UUID id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    @Override
    public Offer create(OfferRequest request) {
        final Offer offer = modelMapper.map(request, Offer.class);
        final Activity activity = activityService.findById(request.activityId());

        offer.setActivity(activity);

        final Offer savedOffer = offerRepository.save(offer);

        final OpenTime openTime = openTimeService.create(request.openTime(), savedOffer);
        savedOffer.setOpenTime(new ArrayList<>(List.of(openTime)));

        final IOfferChoice offerChoice;
        if (OfferType.DEAL.equals(request.offerable().type())) {
            offerChoice = dealService.create(request.offerable().deal());
        } else if (OfferType.BOX.equals(request.offerable().type())) {
            offerChoice = boxService.create(request.offerable().box());
        } else {
            throw new IllegalArgumentException("Invalid offer type");
        }

        savedOffer.setOfferChoice(offerChoice);
        savedOffer.setOfferable(new Offerable(
                offerChoice.getId(),
                request.offerable().type()
        ));

        return offerRepository.save(savedOffer);
    }

    @Override
    public Offer update(UUID id, OfferRequest request) {
        final Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id));

        final Activity activity = activityService.findById(request.activityId());
        final OpenTime openTime = openTimeService.create(request.openTime(), existingOffer);

        existingOffer
                .setPrice(request.price())
                .setSalePrice(request.salePrice())
                .setReduction(request.reduction())
                .setBarcode(request.barcode())
                .setOpenTime(List.of(openTime))
                .setActivity(activity);

        final Offerable newOfferable = new Offerable(
                existingOffer.getOfferable().id(),
                request.offerable().type()
        );

        final IOfferChoice updatedOfferChoice;
        if (OfferType.DEAL.equals(request.offerable().type())) {
            updatedOfferChoice = dealService.update(existingOffer.getOfferable().id(), request.offerable().deal());
        } else if (OfferType.BOX.equals(request.offerable().type())) {
            updatedOfferChoice = boxService.create(request.offerable().box());
        } else {
            throw new IllegalArgumentException("Invalid offer type");
        }

        existingOffer
                .setOfferChoice(updatedOfferChoice)
                .setOfferable(newOfferable);

        return offerRepository.save(existingOffer);
    }


    @Override
    public void delete(UUID id) {
        if (offerRepository.existsById(id))
            offerRepository.deleteById(id);

        offerRepository.softDelete(id);
    }
}
