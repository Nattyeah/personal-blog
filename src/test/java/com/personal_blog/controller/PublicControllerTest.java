package com.personal_blog.controller;

import com.personal_blog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PublicControllerTest {

    @Test
    void rendersHomeWithArticles() {
        ArticleService service = mock(ArticleService.class);
        Model model = mock(Model.class);

        assertThat(new HomeController(service).home(model, null)).isEqualTo("home");
        verify(service).getAllArticles(null);
    }

    @Test
    void rendersLogin() {
        assertThat(new LoginController().login()).isEqualTo("login");
    }
}
