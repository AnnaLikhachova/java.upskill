package com.likhachova.util;

import com.likhachova.dao.jdbc.JdbcGenreDao;
import com.likhachova.service.GenreService;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CacheGenreManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CacheGenreManagerTest.class);

    private GenreService genreService;

    @BeforeEach
    public void before() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:~/movies1;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        assertNotNull(dataSource);
        JdbcGenreDao genreDao = new JdbcGenreDao(dataSource);
        genreService = new GenreService(genreDao);
    }

    @Test
    @DisplayName("Get all genres and check cache")
    public void test_getAllGenres() {

        genreService.findAll();
        logger.info("getting genres from cache:");
        genreService.findAll().forEach(u -> logger.info("{}", u.toString()));
    }
}
