package net.foodeals.contentManagement.infrastructure.modelMapper;

import jakarta.annotation.PostConstruct;
import net.foodeals.contentManagement.application.Dto.response.ArticleCategoryDto;
import net.foodeals.contentManagement.application.Dto.response.ArticleDto;
import net.foodeals.contentManagement.domain.entities.Article;
import net.foodeals.contentManagement.domain.entities.ArticleCategory;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentManagementModelMapperConfig {

    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    private void postConstruct() {
        mapper.addMappings(new PropertyMap<Article, ArticleDto>() {
            @Override
            protected void configure() {
                map(source.getId(), destination.getId());
                map(source.getTitle(), destination.getTitle());
                map(source.getContent(), destination.getContent());
                map(source.getSlug(), destination.getSlug());
                map(source.getCategory().getName(), destination.getCategory());
            }
        });

        // Mapping for ArticleCategory to ArticleCategoryDTO
        mapper.addMappings(new PropertyMap<ArticleCategory, ArticleCategoryDto>() {
            @Override
            protected void configure() {
                map(source.getId(), destination.getId());
                map(source.getName(), destination.getName());
                map(source.getSlug(), destination.getSlug());
                map(source.getArticles(), destination.getArticleDto());
            }
        });
    }
}
