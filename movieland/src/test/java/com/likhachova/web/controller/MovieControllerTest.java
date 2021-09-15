package com.likhachova.web.controller;

import com.likhachova.model.Movie;
import com.likhachova.service.MovieService;
import com.likhachova.util.MovieRequester;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class MovieControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MovieService movieService;

    @BeforeEach
    void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.movieService = mock(MovieService.class);
    }

    @Test
    void getContext() {
        assertTrue(webApplicationContext.getServletContext() instanceof MockServletContext);
    }

    @Test
    @DisplayName("Get all movies and check fields")
    void getAll() throws Exception {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();

        when(movieService.findAll()).thenReturn(new ArrayList<>(25));
        mockMvc.perform(get("/api/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(25)))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is("" + movie.getYearOfRelease() + "")))
                .andExpect(jsonPath("$[0].description", is(movie.getDescription())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())))
                .andExpect(jsonPath("$[0].votes", is(movie.getVotes())));

        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("Get three random movies and check list size")
    void getThreeRandom() throws Exception {

        when(movieService.findThreeRandom(3)).thenReturn(new ArrayList<>(3));
        mockMvc.perform(get("/v1/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(3)));

        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("Get movies by genre and check fields")
    void getMoviesByGenre() throws Exception {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();

        MovieRequester movieRequester = new MovieRequester.Builder().withGenreId(1).build();

        when(movieService.findByGenre(movieRequester)).thenReturn(new ArrayList<>(16));

        mockMvc.perform(get("/v1/movie/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(16)))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is("" + movie.getYearOfRelease() + "")))
                .andExpect(jsonPath("$[0].description", is(movie.getDescription())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())))
                .andExpect(jsonPath("$[0].votes", is(movie.getVotes())));

        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("Sort movies by rating")
    void test_sortMoviesByRating() throws Exception {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withSortingOrder("desc").build();

        when(movieService.sortByRating(movieRequester)).thenReturn(new ArrayList<>(25));

        mockMvc.perform(get("/v1/movie/rating=desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(25)))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is("" + movie.getYearOfRelease() + "")))
                .andExpect(jsonPath("$[0].description", is(movie.getDescription())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())))
                .andExpect(jsonPath("$[0].votes", is(movie.getVotes())));

        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("Sort movies by price")
    void test_sortMoviesByPrice() throws Exception {
        Movie movie = Movie.builder()
                .id(3)
                .nameRussian("Форрест Гамп")
                .nameNative("Forrest Gump")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("От лица главного героя Форреста Гампа, слабоумного безобидного человека с благородным и открытым сердцем, рассказывается история его необыкновенной жизни.Фантастическим образом превращается он в известного футболиста, героя войны, преуспевающего бизнесмена. Он становится миллиардером, но остается таким же бесхитростным, глупым и добрым. Форреста ждет постоянный успех во всем, а он любит девочку, с которой дружил в детстве, но взаимность приходит слишком поздно.")
                .rating(8.6)
                .price(200.6)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg")
                .votes(100)
                .build();

        MovieRequester movieRequester = new MovieRequester.Builder().withSortingOrder("desc").build();

        when(movieService.sortByRating(movieRequester)).thenReturn(new ArrayList<>(25));

        mockMvc.perform(get("/v1/movie/price=desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(25)))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is("" + movie.getYearOfRelease() + "")))
                .andExpect(jsonPath("$[0].description", is(movie.getDescription())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())))
                .andExpect(jsonPath("$[0].votes", is(movie.getVotes())));

        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("Get movie by id and check fields")
    void getMovieById() throws Exception {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();

        MovieRequester movieRequester = new MovieRequester.Builder().withMovieId(1).build();

        when(movieService.findById(movieRequester)).thenReturn(movie);

        mockMvc.perform(get("/v1/movie/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(movie.getId())))
                .andExpect(jsonPath("$.nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$.nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$.yearOfRelease", is("" + movie.getYearOfRelease() + "")))
                .andExpect(jsonPath("$.description", is(movie.getDescription())))
                .andExpect(jsonPath("$.rating", is(movie.getRating())))
                .andExpect(jsonPath("$.price", is(movie.getPrice())))
                .andExpect(jsonPath("$.picturePath", is(movie.getPicturePath())))
                .andExpect(jsonPath("$.votes", is(movie.getVotes())));

        verifyNoMoreInteractions(movieService);
    }
}
