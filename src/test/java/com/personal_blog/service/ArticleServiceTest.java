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

import java.time.LocalDate;
import java.util.Optional;

import static com.personal_blog.mocks.ArticleMock.articleWithFixedTitle;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    ArticleRepository repository;
    @Mock
    ArticleMapper mapper;
    @InjectMocks
    ArticleService service;

    @Test
    void createsFutureArticle() {
        Article article = articleWithFixedTitle(LocalDate.now().plusDays(1));
        ArticleDto expected = new ArticleDto(1L, "Novo", article.getPublishDate(), "Conteúdo");

        when(repository.save(article)).thenReturn(article);
        when(mapper.toDto(article)).thenReturn(expected);

        assertThat(service.create(article)).isEqualTo(expected);
        verify(repository).save(article);
    }

    @Test
    void rejectsPastDate() {
        assertThatThrownBy(() -> service.create(articleWithFixedTitle(LocalDate.now().minusDays(1))))
                .isInstanceOf(IllegalArgumentException.class);

        verifyNoInteractions(repository, mapper);
    }

    @Test
    void updatesExistingArticle() {
        Article existing = articleWithFixedTitle(LocalDate.now());
        Article changes = articleWithFixedTitle(LocalDate.now().plusDays(1));
        changes.setTitle("Atualizado");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        assertThat(service.update(changes, 1L)).isSameAs(existing);
        assertThat(existing.getTitle()).isEqualTo("Atualizado");
    }
}
