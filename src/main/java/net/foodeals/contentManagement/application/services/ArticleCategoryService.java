package net.foodeals.contentManagement.application.services;

import net.foodeals.contentManagement.application.Dto.upload.UpdateArticleCategoryDto;
import net.foodeals.common.Utils.SlugUtil;
import net.foodeals.contentManagement.application.Dto.upload.AddArticleToCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.CreateArticleCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.DeleteArticleFromCategoryDto;
import net.foodeals.contentManagement.domain.entities.Article;
import net.foodeals.contentManagement.domain.entities.ArticleCategory;
import net.foodeals.contentManagement.domain.repositories.ArticleCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleCategoryService {

    private final ArticleCategoryRepository articleCategoryRepository;
    private final ArticleService articleService;

    public ArticleCategoryService(ArticleCategoryRepository articleCategoryRepository, ArticleService articleService) {
        this.articleCategoryRepository = articleCategoryRepository;
        this.articleService = articleService;
    }

    public List<ArticleCategory> getAllArticleCategories() {
        return this.articleCategoryRepository.findAll();
    }

    public ArticleCategory getArticleByUuid(String uuid) {
        UUID uuidObj = UUID.fromString(uuid);
        ArticleCategory articleCategory = this.articleCategoryRepository.findById(uuidObj).orElse(null);

        if (articleCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article Category not found");
        }

        return articleCategory;
    }

    public ArticleCategory createAnArticleCategory(CreateArticleCategoryDto createArticleCategoryDto) {
        ArticleCategory articleCategory = ArticleCategory.builder().name(createArticleCategoryDto.getName()).build();

        String generatedSlug = SlugUtil.toSlug(createArticleCategoryDto.getName());
        articleCategory.setSlug(SlugUtil.makeUniqueSlug(generatedSlug, this.articleCategoryRepository));

        return this.articleCategoryRepository.save(articleCategory);
    }

    public ArticleCategory updateAnArticleCategoryById(String uuid, UpdateArticleCategoryDto updateArticleCategoryDto) {
        UUID uuidObj = UUID.fromString(uuid);
        ArticleCategory articleCategory = this.articleCategoryRepository.findById(uuidObj).orElse(null);
        if (articleCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article Category not found");
        }

        if (updateArticleCategoryDto.getName().length() != 0) {
            articleCategory.setName(updateArticleCategoryDto.getName());
            String generatedSlug = SlugUtil.toSlug(updateArticleCategoryDto.getName());
            articleCategory.setSlug(SlugUtil.makeUniqueSlug(generatedSlug, this.articleCategoryRepository));
        }

        return this.articleCategoryRepository.save(articleCategory);
    }

    public ArticleCategory deleteAnArticleCategoryByUuid(String uuid) {
        UUID uuidObj = UUID.fromString(uuid);
        ArticleCategory articleCategory = this.articleCategoryRepository.findById(uuidObj).orElse(null);
        if (articleCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article Category not found");
        }

        this.articleCategoryRepository.delete(articleCategory);
        return articleCategory;
    }

    public void addAnArticleToCategory(String uuid, AddArticleToCategoryDto addArticleToCategoryDto) {
        Article article = this.articleService.getArticleByUuid(addArticleToCategoryDto.getArticleUuid());
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article Not Found");
        }

        UUID uuidObj = UUID.fromString(uuid);
        ArticleCategory articleCategory = this.articleCategoryRepository.findById(uuidObj).orElse(null);
        if (articleCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Article Category Not Found");
        }

        List<Article> articles = articleCategory.getArticles();
        article.setCategory(articleCategory);
        articles.add(article);
        articleCategory.setArticles(articles);
        this.articleCategoryRepository.save(articleCategory);
    }

    public void deleteAnArticleFromCategory(String categoryId, DeleteArticleFromCategoryDto deleteArticleFromCategoryDto) {
        Article article = this.articleService.getArticleByUuid(deleteArticleFromCategoryDto.getArticleUuid());
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article Not Found");
        }
        UUID uuidObj = UUID.fromString(categoryId);
        ArticleCategory articleCategory = this.articleCategoryRepository.findById(uuidObj).orElse(null);
        if (articleCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Article Category Not Found");
        }

        List<Article> articles = articleCategory.getArticles();
        articles.remove(article);
        article.setCategory(null);
        this.articleService.save(article);
        articleCategory.setArticles(articles);
        this.articleCategoryRepository.save(articleCategory);
    }
}
