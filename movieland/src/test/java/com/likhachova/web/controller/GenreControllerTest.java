package com.likhachova.web.controller;

import com.likhachova.model.Genre;
import com.likhachova.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:/src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class GenreControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private GenreService genreService;

    @BeforeEach
    void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.genreService = mock(GenreService.class);
    }

    @Test
    void getContext() {
        assertTrue(webApplicationContext.getServletContext() instanceof MockServletContext);
    }

    @Test
    @DisplayName("Get all genres and check fields")
    void getAll() throws Exception {
        Genre genreFirst = Genre.builder()
                .id(1)
                .name("драма")
                .build();

        Genre genreSecond = Genre.builder()
                .id(2)
                .name("криминал")
                .build();

        when(genreService.findAll()).thenReturn(new ArrayList<>(15));
        mockMvc.perform(get("/v1/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(15)))
                .andExpect(jsonPath("$[0].id", is(genreFirst.getId())))
                .andExpect(jsonPath("$[0].name", is(genreFirst.getName())))
                .andExpect(jsonPath("$[1].id", is(genreSecond.getId())))
                .andExpect(jsonPath("$[1].name", is(genreSecond.getName())));

        verifyNoMoreInteractions(genreService);
    }
}
