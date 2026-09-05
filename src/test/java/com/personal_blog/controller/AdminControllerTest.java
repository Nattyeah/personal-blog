package com.personal_blog.controller;

import com.personal_blog.model.entity.Article;
import com.personal_blog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static com.personal_blog.mocks.ArticleMock.article;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    ArticleService service;
    @Mock
    BindingResult result;
    @InjectMocks
    AdminController controller;

    @Test
    void returnsFormWhenCreateIsInvalid() {
        when(result.hasErrors()).thenReturn(true);

        assertThat(controller.create(article(), result)).isEqualTo("add-article");
        verifyNoInteractions(service);
    }

    @Test
    void createsUpdatesAndDeletesWhenValid() {
        when(result.hasErrors()).thenReturn(false);
        Article article = article();

        assertThat(controller.create(article, result)).isEqualTo("redirect:/dashboard");
        assertThat(controller.update(article, 1L, result)).isEqualTo("redirect:/dashboard");
        assertThat(controller.deleteArticle(1L)).isEqualTo("redirect:/dashboard");
        verify(service).create(article);
        verify(service).update(article, 1L);
        verify(service).delete(1L);
    }
}
