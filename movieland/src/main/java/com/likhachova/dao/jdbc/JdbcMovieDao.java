package com.likhachova.dao.jdbc;

import com.likhachova.dao.MovieDao;
import com.likhachova.dao.jdbc.mapper.MovieRowMapper;
import com.likhachova.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMovieDao.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final String SELECT_ALL = "SELECT * FROM movie;";
    private static final String SELECT_THREE_RANDOM = "SELECT id, name_russian, name_native, year_of_release, description, rating, price, picture_path, votes FROM movie ORDER BY RAND() limit ?;";


    public JdbcMovieDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public List<Movie> findAll() {
        logger.debug("Get all movies from database");
        return jdbcTemplate.query(SELECT_ALL, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> findThreeRandom(int limit) {
        logger.debug("Find three random movies from database");
        return jdbcTemplate.query(SELECT_THREE_RANDOM, new MovieRowMapper(), limit);
    }

}
