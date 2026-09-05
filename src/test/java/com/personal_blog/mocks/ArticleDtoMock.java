package com.personal_blog.mocks;

import com.personal_blog.model.ArticleDto;
import com.personal_blog.model.entity.Article;

public class ArticleDtoMock {

    public static ArticleDto dto(Article article, Long id) {
        return new ArticleDto(id, article.getTitle(), article.getPublishDate(), article.getContent());
    }
}
