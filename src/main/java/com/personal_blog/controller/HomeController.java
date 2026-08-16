package com.personal_blog.controller;

import com.personal_blog.model.ArticleDto;
import com.personal_blog.model.UserDto;
import com.personal_blog.model.entity.User;
import com.personal_blog.service.ArticleService;
import com.personal_blog.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class HomeController {

    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

//    TODO ver como ajustar para ser o de login
    @GetMapping("/articles")
    public ResponseEntity<Page<ArticleDto>> getAllArticles(Pageable pageable) {
        return ResponseEntity.ok().body(articleService.getAllArticles(pageable));
    }
}
