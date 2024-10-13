package net.foodeals.config;

import net.foodeals.contentManagement.application.Dto.response.ArticleCategoryDto;
import net.foodeals.contentManagement.application.Dto.response.ArticleDto;
import net.foodeals.contentManagement.domain.entities.Article;
import net.foodeals.contentManagement.domain.entities.ArticleCategory;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.record.RecordModule;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.registerModule(new RecordModule());
        mapper.addConverter(new Converter<UUID, String>() {
            @Override
            public String convert(MappingContext<UUID, String> context) {
                return context.getSource() != null ? context.getSource().toString() : null;
            }
        });

        return mapper;
    }

}
