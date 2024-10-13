package net.foodeals.offer.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.requests.DealDto;
import net.foodeals.offer.application.services.DealService;
import net.foodeals.offer.domain.entities.Deal;
import net.foodeals.offer.domain.exceptions.DealNotFoundException;
import net.foodeals.offer.domain.repositories.DealRepository;
import net.foodeals.product.application.services.ProductService;
import net.foodeals.product.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class DealServiceImpl implements DealService {

    private final DealRepository repository;
    private final ProductService productService;

    @Override
    public Deal findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DealNotFoundException(id));
    }

    @Override
    public Deal create(DealDto dto) {
        final Product product = productService.findById(dto.productId());
        final Deal deal = Deal.create(dto.price(), dto.quantity(), product);
        return repository.save(deal);
    }

    @Override
    public Deal update(UUID id, DealDto dto) {
        final Deal deal = findById(id);
        final Product product = productService.findById(dto.productId());

        deal.setPrice(dto.price())
                .setQuantity(dto.quantity())
                .setProduct(product);

        return repository.save(deal);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
                throw new DealNotFoundException(id);

        repository.softDelete(id);
    }
}
