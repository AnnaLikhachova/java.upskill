package com.likhachova.dao.jdbc;

import com.likhachova.dao.jdbc.mapper.MovieRowMapper;
import com.likhachova.model.Movie;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

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
        dataSource.setUrl("jdbc:h2:~/movies;DB_CLOSE_DELAY=-1");
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

}
