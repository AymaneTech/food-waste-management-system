package net.foodeals.contentManagement.application.Dto.response;

import lombok.Data;

@Data
public class ArticleDto {

    private String id;

    private String title;

    private String slug;

    private String content;

    private String category;
}
