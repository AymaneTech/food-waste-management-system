package net.foodeals.contentManagement.infrastructure.controllers;

import net.foodeals.contentManagement.application.Dto.response.ArticleCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.AddArticleToCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.CreateArticleCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.DeleteArticleFromCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.UpdateArticleCategoryDto;
import net.foodeals.contentManagement.application.Dto.upload.*;
import net.foodeals.contentManagement.domain.entities.ArticleCategory;
import net.foodeals.contentManagement.application.services.ArticleCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ArticleCategoriesController {

    private final ArticleCategoryService articleCategoryService;
    private final ModelMapper modelMapper;


    public ArticleCategoriesController(ArticleCategoryService articleCategoryService, ModelMapper modelMapper) {
        this.articleCategoryService = articleCategoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/ArticleCategories")
    public ResponseEntity<List<ArticleCategoryDto>> getAllCategories() {
        List<ArticleCategory> articleCategories = this.articleCategoryService.getAllArticleCategories();
        List<ArticleCategoryDto> articleCategoryDto = articleCategories.stream()
                .map(articleCategory -> modelMapper.map(articleCategory, ArticleCategoryDto.class))
                .toList();
        return new ResponseEntity<List<ArticleCategoryDto>>(articleCategoryDto, HttpStatus.OK);
    }

    @GetMapping("/ArticleCategory/{uuid}")
    public ResponseEntity<ArticleCategoryDto> getArticleCategoryByUuid(@PathVariable("uuid") String uuid) {
        ArticleCategory articleCategory = this.articleCategoryService.getArticleByUuid(uuid);

        if (articleCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticleCategory Not Found");
        }
        ArticleCategoryDto articleCategoryDto = this.modelMapper.map(articleCategory, ArticleCategoryDto.class);
        return new ResponseEntity<ArticleCategoryDto>(articleCategoryDto, HttpStatus.OK);
    }

    @PostMapping("/ArticleCategory")
    public ResponseEntity<ArticleCategoryDto> createAnArticleCategory(@RequestBody CreateArticleCategoryDto createArticleCategoryDto) {
        ArticleCategory articleCategory = this.articleCategoryService.createAnArticleCategory(createArticleCategoryDto);
        ArticleCategoryDto articleCategoryDto = this.modelMapper.map(articleCategory, ArticleCategoryDto.class);
        return new ResponseEntity<ArticleCategoryDto>(articleCategoryDto, HttpStatus.OK);
    }

    @PutMapping("ArticleCategory/{uuid}")
    public ResponseEntity<ArticleCategoryDto> updateAnArticle(@PathVariable("uuid") String uuid, @RequestBody UpdateArticleCategoryDto updateArticleCategoryDto) {
        ArticleCategory articleCategory = this.articleCategoryService.updateAnArticleCategoryById(uuid, updateArticleCategoryDto);
        ArticleCategoryDto articleCategoryDto = this.modelMapper.map(articleCategory, ArticleCategoryDto.class);
        return new ResponseEntity<ArticleCategoryDto>(articleCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("ArticleCategory/{uuid}")
    public ResponseEntity<ArticleCategoryDto> deleteAnArticleCategory(@PathVariable("uuid") String uuid) {
        ArticleCategory articleCategory = this.articleCategoryService.deleteAnArticleCategoryByUuid(uuid);
        ArticleCategoryDto articleCategoryDto = this.modelMapper.map(articleCategory, ArticleCategoryDto.class);
        return new ResponseEntity<ArticleCategoryDto>(articleCategoryDto, HttpStatus.OK);
    }

    @PostMapping("/ArticleCategory/{CategoryId}/addArticle")
    public ResponseEntity<String> addAnArticleToCategory(@PathVariable("CategoryId") String categoryId, @RequestBody AddArticleToCategoryDto addArticleToCategoryDto) {
        this.articleCategoryService.addAnArticleToCategory(categoryId, addArticleToCategoryDto);
        return new ResponseEntity<String>("Article was added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/ArticleCategory/{CategoryId}/deleteArticle")
    public ResponseEntity<String> DeleteAnArticleFromCategory(@PathVariable("CategoryId") String categoryId, @RequestBody DeleteArticleFromCategoryDto deleteArticleFromCategoryDto) {
        this.articleCategoryService.deleteAnArticleFromCategory(categoryId, deleteArticleFromCategoryDto);
        return new ResponseEntity<String>("Article was added successfully", HttpStatus.OK);
    }
}
