package net.foodeals.contentManagement.infrastructure.controllers;

import net.foodeals.contentManagement.application.Dto.response.ArticleDto;
import net.foodeals.contentManagement.application.Dto.upload.CreateArticleDto;
import net.foodeals.contentManagement.application.Dto.upload.UpdateArticleDto;
import net.foodeals.contentManagement.domain.entities.Article;
import net.foodeals.contentManagement.application.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller()
public class ArticlesController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    public ArticlesController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/Articles")
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<Article> articles = this.articleService.getAllArticles();
        List<ArticleDto> articlesDto = articles.stream().map(article -> this.modelMapper.map(article, ArticleDto.class)).toList();
        return new ResponseEntity<List<ArticleDto>>(articlesDto, HttpStatus.OK);
    }

    @GetMapping("/Article/{uuid}")
    public ResponseEntity<ArticleDto> getArticleByUuid(@PathVariable("uuid") String uuid) {
        Article article = this.articleService.getArticleByUuid(uuid);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article Not Found");
        }

        ArticleDto articleDto = this.modelMapper.map(article, ArticleDto.class);
        return new ResponseEntity<ArticleDto>(articleDto, HttpStatus.OK);
    }

    @PostMapping("/Articles")
    public ResponseEntity<ArticleDto> createAnArticle(@RequestBody CreateArticleDto createArticleDto) {
        Article article = this.articleService.createAnArticle(createArticleDto);
        ArticleDto articleDto = this.modelMapper.map(article, ArticleDto.class);
        return new ResponseEntity<ArticleDto>(articleDto, HttpStatus.OK);
    }

    @PutMapping("Article/{uuid}")
    public ResponseEntity<ArticleDto> updateAnArticle(@PathVariable("uuid") String uuid, @RequestBody UpdateArticleDto updateArticleDto) {
        Article article = this.articleService.updateAnArticleByUuid(uuid, updateArticleDto);
        ArticleDto articleDto = this.modelMapper.map(article, ArticleDto.class);
        return new ResponseEntity<ArticleDto>(articleDto, HttpStatus.OK);
    }

    @DeleteMapping("Article/{uuid}")
    public ResponseEntity<ArticleDto> deleteAnArticle(@PathVariable("uuid") String uuid) {
        Article article = this.articleService.deleteAnArticleByUuid(uuid);
        ArticleDto articleDto = this.modelMapper.map(article, ArticleDto.class);
        return new ResponseEntity<ArticleDto>(articleDto, HttpStatus.OK);
    }
}
