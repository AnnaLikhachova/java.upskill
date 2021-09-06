package com.likhachova.dao.jdbc;

import com.likhachova.dao.jdbc.mapper.MovieRowMapper;
import com.likhachova.model.Movie;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JdbcMovieDaoTest {

    @Mock
    JdbcMovieDao jdbcMovieDao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    MovieRowMapper movieRowMapper;

    @BeforeEach
    public void before() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:~/movies1;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        assertNotNull(dataSource);
        jdbcMovieDao = new JdbcMovieDao(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        movieRowMapper = new MovieRowMapper();
    }
    @Test
    public void test_MockCreation(){
        assertNotNull(jdbcMovieDao);
        assertNotNull(jdbcTemplate);
        assertNotNull(movieRowMapper);
    }

    @Test
    @DisplayName("Get all movies")
    public void whenMockJdbcMovieDao_thenReturnCorrectMoviesCount() {
        List<Movie> movies = jdbcMovieDao.findAll();
        assertNotNull(movies);
        assertEquals(25, movies.size());
    }

    @Test
    @DisplayName("Get three random movies")
    public void whenMockJdbcMovieDao_thenReturnTreeRandomMovies() {
        List<Movie> movies = jdbcMovieDao.findThreeRandom(3);
        assertEquals(3, movies.size());
    }

    @Test
    @DisplayName("Get movies by genre")
    public void whenMockJdbcMovieDao_thenReturnMoviesByGenre() {
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
        List<Movie> movies = jdbcMovieDao.findByGenre(1);
        assertEquals(movie.getId(), movies.get(0).getId());
        assertEquals(movie.getNameNative(), movies.get(0).getNameNative());
        assertEquals(movie.getNameRussian(), movies.get(0).getNameRussian());
        assertEquals(movie.getDescription(), movies.get(0).getDescription());
        assertEquals(movie.getPrice(), movies.get(0).getPrice());
        assertEquals(movie.getVotes(), movies.get(0).getVotes());
        assertEquals(movie.getYearOfRelease(), movies.get(0).getYearOfRelease());
        assertEquals(movie.getPicturePath(), movies.get(0).getPicturePath());
        assertEquals(movie.getRating(), movies.get(0).getRating());
    }

}
