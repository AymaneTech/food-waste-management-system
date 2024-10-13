package net.foodeals.offer.infrastructure.modelMapperConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.foodeals.offer.application.dtos.responses.BoxItemResponse;
import net.foodeals.offer.application.dtos.responses.DealResponse;
import net.foodeals.offer.application.dtos.responses.OfferResponse;
import net.foodeals.offer.application.dtos.responses.OpenTimeResponse;
import net.foodeals.offer.domain.entities.Box;
import net.foodeals.offer.domain.entities.Deal;
import net.foodeals.offer.domain.entities.Offer;
import net.foodeals.offer.domain.entities.OpenTime;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OfferModelMapperConfig {

    private final ModelMapper mapper;

    @PostConstruct
    public void configure() {
        mapper.addConverter(context -> {
            final OpenTime openTime = context.getSource();
            return new OpenTimeResponse(
                    openTime.getId(),
                    openTime.getDay(),
                    openTime.getFrom(),
                    openTime.getTo()
            );
        }, OpenTime.class, OpenTimeResponse.class);

        mapper.addConverter(context -> {
            final Box box = context.getSource();
            final List<BoxItemResponse> items = box.getBoxItems()
                    .stream().
                    map((element) -> mapper.map(element, BoxItemResponse.class))
                    .toList();
            return new BoxResponse(
                    box.getId(),
                    items
            );
        }, Box.class, BoxResponse.class);

        mapper.addConverter(context -> {
            Deal deal = context.getSource();
            return new DealResponse(
                    deal.getId(),
                    deal.getPrice(),
                    deal.getQuantity(),
                    deal.getProduct()
            );
        }, Deal.class, DealResponse.class);

        mapper.addConverter(context -> {
            final Offer offer = context.getSource();
            final List<OpenTimeResponse> openTimeResponses = offer.getOpenTime()
                    .stream()
                    .map((element) -> mapper.map(element, OpenTimeResponse.class))
                    .toList();

            return new OfferResponse(
                    offer.getId(),
                    offer.getPrice(),
                    offer.getSalePrice(),
                    offer.getReduction(),
                    offer.getBarcode(),
                    openTimeResponses,
                    offer.getOfferable(),
                    offer.getOfferable().type()
            );
        }, Offer.class, OfferResponse.class);
    }
}
