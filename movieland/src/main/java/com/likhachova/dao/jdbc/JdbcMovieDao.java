package com.likhachova.dao.jdbc;

import com.likhachova.dao.MovieDao;
import com.likhachova.dao.jdbc.mapper.MovieRowMapper;
import com.likhachova.model.Movie;
import com.likhachova.util.MovieRequester;
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
    private static final String SELECT_MOVIES_BY_GENRE = "SELECT movie.id, name_russian, name_native, year_of_release, description, rating, price, picture_path, votes FROM movie JOIN movie_genre ON movie.id = movie_genre.movie_id JOIN genre ON genre.id = movie_genre.genre_id WHERE genre.id = ?;";
    private static final String SORT_MOVIES_BY_PRICE = "SELECT * FROM movie ORDER BY price %s;";
    private static final String SORT_MOVIES_BY_RATING = "SELECT * FROM movie ORDER BY rating %s;";
    private static final String SELECT_MOVIES_BY_GENRE_SORT_BY_PRICE = "SELECT movie.id, name_russian, name_native, year_of_release, description, rating, price, picture_path, votes FROM movie JOIN movie_genre ON movie.id = movie_genre.movie_id JOIN genre ON genre.id = movie_genre.genre_id WHERE genre.id = ? ORDER BY price %s;";
    private static final String SELECT_MOVIES_BY_GENRE_SORT_BY_RATING = "SELECT movie.id, name_russian, name_native, year_of_release, description, rating, price, picture_path, votes FROM movie JOIN movie_genre ON movie.id = movie_genre.movie_id JOIN genre ON genre.id = movie_genre.genre_id WHERE genre.id = ? ORDER BY rating %s;";
    private static final String SELECT_MOVIE_BY_ID = "SELECT movie.id, name_russian, name_native, year_of_release, description, rating, price, picture_path, votes FROM movie WHERE id = ?;";

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

    @Override
    public List<Movie> findByGenre(MovieRequester movieRequester) {
        logger.debug("Find movies by genre from database");
        return jdbcTemplate.query(SELECT_MOVIES_BY_GENRE, new MovieRowMapper(), movieRequester.getGenreId());
    }

    @Override
    public List<Movie> sortByPrice(MovieRequester movieRequester) {
        logger.debug("Sort movies by price");
        String sql = String.format(SORT_MOVIES_BY_PRICE, movieRequester.getSortingOrder());
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public List<Movie> sortByRating(MovieRequester movieRequester) {
        logger.debug("Sort movies by rating");
        String sql = String.format(SORT_MOVIES_BY_RATING, movieRequester.getSortingOrder());
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public List<Movie> findByGenreSortByPrice(MovieRequester movieRequester) {
        logger.debug("Find movies by genre and sort movies by price");
        String sql = String.format(SELECT_MOVIES_BY_GENRE_SORT_BY_PRICE, movieRequester.getSortingOrder());
        return jdbcTemplate.query(sql, new MovieRowMapper(), movieRequester.getGenreId());
    }

    @Override
    public List<Movie> findByGenreSortByRating(MovieRequester movieRequester) {
        logger.debug("Find movies by genre and sort movies by rating");
        String sql = String.format(SELECT_MOVIES_BY_GENRE_SORT_BY_RATING, movieRequester.getSortingOrder());
        return jdbcTemplate.query(sql, new MovieRowMapper(), movieRequester.getGenreId());
    }

    @Override
    public Movie findById(MovieRequester movieRequester) {
        logger.debug("Find movie by id");
        return  jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID, new MovieRowMapper(), movieRequester.getMovieId());
    }

}
