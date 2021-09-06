package com.likhachova.dao.jdbc;

import com.likhachova.model.Genre;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JdbcGenreDaoTest {

    @Mock
    JdbcGenreDao jdbcGenreDao;

    @BeforeEach
    public void before() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:~/movies;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        assertNotNull(dataSource);
        jdbcGenreDao = new JdbcGenreDao(dataSource);
    }

    @Test
    public void test_MockCreation(){
        assertNotNull(jdbcGenreDao);
    }

    @Test
    @DisplayName("Get all genres")
    public void whenMockJdbcMovieDao_thenReturnCorrectMoviesCount() {
        List<Genre> genres = jdbcGenreDao.findAll();
        assertNotNull(genres);
        assertEquals(15, genres.size());
    }

}
