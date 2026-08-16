package com.personal_blog.service;

import com.personal_blog.mapper.ArticleMapper;
import com.personal_blog.model.ArticleDto;
import com.personal_blog.model.entity.Article;
import com.personal_blog.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final ArticleMapper mapper;

    public ArticleService(ArticleRepository repository, ArticleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //    ADD
    public ArticleDto create(Article article) {
        return mapper.toDto(repository.save(article));
    }

    //    EDIT
    public Article update(Article article, Long id) {
        return repository.findById(id).stream().map(article1 -> {
            article1.setTitle(article.getTitle());
            article1.setPublishDate(article.getPublishDate());
            article1.setContent(article.getContent());
            return repository.save(article1);
        }).findFirst().orElse(null);
    }

    //    GET ALL
    public Page<ArticleDto> getAllArticles(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    //    GET BY ID
    public ArticleDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    //    DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
