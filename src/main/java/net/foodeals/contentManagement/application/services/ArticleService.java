package net.foodeals.contentManagement.application.services;


import net.foodeals.contentManagement.application.Dto.upload.CreateArticleDto;
import net.foodeals.contentManagement.application.Dto.upload.UpdateArticleDto;
import net.foodeals.common.Utils.SlugUtil;
import net.foodeals.contentManagement.domain.entities.Article;
import net.foodeals.contentManagement.domain.repositories.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return this.articleRepository.findAll();
    }

    public Article getArticleByUuid(String uuid) {
        UUID uuidObj = UUID.fromString(uuid);
        return this.articleRepository.findById(uuidObj).orElse(null);
    }

    public Article createAnArticle(CreateArticleDto createArticleDto) {
        Article article = Article.builder().title(createArticleDto.getTitle())
                .content(createArticleDto.getContent()).build();
        String generatedSlug = SlugUtil.toSlug(createArticleDto.getTitle());
        article.setSlug(SlugUtil.makeUniqueSlug(generatedSlug, this.articleRepository));

        return this.articleRepository.save(article);
    }

    public Article deleteAnArticleByUuid(String uuid) {
        UUID uuidObj = UUID.fromString(uuid);
        Article article = this.articleRepository.findById(uuidObj).orElse(null);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article not found");
        }

        this.articleRepository.delete(article);
        return article;
    }

    public Article updateAnArticleByUuid(String uuid, UpdateArticleDto updateArticleDto) {
        UUID uuidObj = UUID.fromString(uuid);
        Article article = this.articleRepository.findById(uuidObj).orElse(null);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article not found");
        }

        if (updateArticleDto.getTitle().length() != 0) {
            article.setTitle(updateArticleDto.getTitle());
            String generatedSlug = SlugUtil.toSlug(updateArticleDto.getTitle());
            article.setSlug(SlugUtil.makeUniqueSlug(generatedSlug, this.articleRepository));
        }

        if (updateArticleDto.getContent().length() != 0) {
            article.setContent(updateArticleDto.getContent());
        }

        return this.articleRepository.save(article);
    }

    public Article save(Article article) {
        return this.articleRepository.save(article);
    }
}
