package com.personal_blog.service;

import com.personal_blog.mapper.ArticleMapper;
import com.personal_blog.model.ArticleDto;
import com.personal_blog.model.entity.Article;
import com.personal_blog.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.personal_blog.mocks.ArticleDtoMock.dto;
import static com.personal_blog.mocks.ArticleMock.articleWithoutFixedTitle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceReadTest {

    @Mock
    ArticleRepository repository;
    @Mock
    ArticleMapper mapper;
    @InjectMocks
    ArticleService service;

    @Test
    void mapsArticlePage() {
        Article article = articleWithoutFixedTitle("Primeiro");
        PageRequest page = PageRequest.of(0, 10);

        when(repository.findAll(page)).thenReturn(new PageImpl<>(List.of(article)));
        when(mapper.toDto(article)).thenReturn(dto(article, 1L));

        assertThat(service.getAllArticles(page).getContent()).extracting(ArticleDto::title)
                .containsExactly("Primeiro");
    }

    @Test
    void mapsFoundArticleAndReturnsNullForMissingOne() {
        Article article = articleWithoutFixedTitle("Detalhe");

        when(repository.findById(1L)).thenReturn(Optional.of(article));
        when(repository.findById(2L)).thenReturn(Optional.empty());
        when(mapper.toDto(article)).thenReturn(dto(article, 1L));
        when(mapper.toDto(null)).thenReturn(null);

        assertThat(service.getById(1L).title()).isEqualTo("Detalhe");
        assertThat(service.getById(2L)).isNull();
    }

    @Test
    void returnsNullForMissingUpdateAndDeletesById() {
        when(repository.findById(8L)).thenReturn(Optional.empty());

        assertThat(service.update(articleWithoutFixedTitle("Novo"), 8L)).isNull();
        service.delete(8L);
        verify(repository).deleteById(8L);
    }
}
