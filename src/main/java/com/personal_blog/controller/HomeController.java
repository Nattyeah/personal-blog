package com.personal_blog.controller;

import com.personal_blog.service.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home(Model model, Pageable pageable) {
        model.addAttribute(
                "articles",
                articleService.getAllArticles(pageable)
        );

        return "home";
    }
}
