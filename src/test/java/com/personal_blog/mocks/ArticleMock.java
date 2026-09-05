package com.personal_blog.mocks;

import com.personal_blog.model.entity.Article;

import java.time.LocalDate;

public class ArticleMock {

    public static Article articleWithFixedTitle(LocalDate date) {
        Article article = new Article();
        article.setTitle("Novo");
        article.setContent("Conteúdo");
        article.setPublishDate(date);
        return article;
    }

    public static Article articleWithoutFixedTitle(String title) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent("Conteúdo");
        article.setPublishDate(LocalDate.now());
        return article;
    }

    public static Article article() {
        Article article = new Article();
        article.setPublishDate(LocalDate.now());
        return article;
    }
}
