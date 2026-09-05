package com.personal_blog.mapper;

import com.personal_blog.model.ArticleDto;
import com.personal_blog.model.entity.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto toDto(Article article);
}
