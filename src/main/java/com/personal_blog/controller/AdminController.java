package com.personal_blog.controller;

import com.personal_blog.model.entity.Article;
import com.personal_blog.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final ArticleService articleService;

    public AdminController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/{id}")
    public String article(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getById(id));
        return "article";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Pageable pageable) {
        model.addAttribute("articles", articleService.getAllArticles(pageable));
        return "dashboard";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "add-article";
    }

    @PostMapping("/articles")
    public String create(@Valid @ModelAttribute Article article,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "add-article";
        }

        articleService.create(article);
        return "redirect:/dashboard";
    }

    @GetMapping("/articles/{id}/edit")
    public String editArticleForm(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getById(id));
        return "edit-article";
    }

    @PostMapping("/articles/{id}")
    public String update(@Valid @ModelAttribute Article article, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-article";
        }

        articleService.update(article, id);
        return "redirect:/dashboard";
    }

    @PostMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/dashboard";
    }
}
