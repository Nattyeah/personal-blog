package com.personal_blog.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

//@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ArticleDto(String title, LocalDateTime publishDate, String content) {

}
