package net.foodeals.product.infrastructure.modelMapperConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.foodeals.organizationEntity.application.dtos.responses.ActivityResponse;
import net.foodeals.product.application.dtos.responses.ProductCategoryResponse;
import net.foodeals.product.application.dtos.responses.ProductResponse;
import net.foodeals.product.domain.entities.Product;
import net.foodeals.product.domain.entities.ProductCategory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductModelMapperConfig {

    private final ModelMapper mapper;

    @PostConstruct
    public void configure() {
        mapper.addConverter(context -> {
            ProductCategory category = context.getSource();
            return new ProductCategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getSlug(),
                    new ActivityResponse(
                            category.getActivity().getId(),
                            category.getActivity().getName()
                    )
            );
        }, ProductCategory.class, ProductCategoryResponse.class);

        mapper.addConverter(context -> {
            Product product = context.getSource();
            return new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getSlug(),
                    product.getTitle(),
                    product.getDescription(),
                    product.getProductImagePath(),
                    mapper.map(product.getCategory(), ProductCategoryResponse.class),
                    product.getBarcode(),
                    product.getType(),
                    product.getPrice()
            );
        }, Product.class, ProductResponse.class);
    }
}
