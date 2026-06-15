package com.personal_blog.controller;

import com.personal_blog.model.ArticleDto;
import com.personal_blog.model.entity.Article;
import com.personal_blog.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleDto> create(@RequestBody Article article) {
        return ResponseEntity.ok().body(articleService.create(article));
    }

    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody Article article, @PathVariable Long id) {
        return ResponseEntity.ok().body(articleService.update(article, id));
    }

    @GetMapping("/articles")
    public ResponseEntity<Page<ArticleDto>> getAllArticles(Pageable pageable) {
        return ResponseEntity.ok().body(articleService.getAllArticles(pageable));
    }
}
