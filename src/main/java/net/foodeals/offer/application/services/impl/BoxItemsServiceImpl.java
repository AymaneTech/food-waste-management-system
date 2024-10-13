package net.foodeals.offer.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.requests.BoxItemDto;
import net.foodeals.offer.application.services.BoxItemService;
import net.foodeals.offer.domain.entities.Box;
import net.foodeals.offer.domain.entities.BoxItem;
import net.foodeals.offer.domain.repositories.BoxItemRepository;
import net.foodeals.product.application.services.ProductService;
import net.foodeals.product.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class BoxItemsServiceImpl implements BoxItemService {

    private final BoxItemRepository repository;
    private final ProductService productService;

    public List<BoxItem> createAll(List<BoxItemDto> dtos, Box box) {
        final List<BoxItem> boxItems = dtos.stream().map(dto -> {
            final Product product = productService.findById(dto.productId());
            return BoxItem.create(dto.price(), dto.quantity(), box, product);
        }).toList();
        return repository.saveAll(boxItems);
    }
}
