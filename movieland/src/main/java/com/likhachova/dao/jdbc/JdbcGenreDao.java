package com.likhachova.dao.jdbc;

import com.likhachova.dao.GenreDao;
import com.likhachova.dao.jdbc.mapper.GenreRowMapper;
import com.likhachova.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcGenreDao implements GenreDao {

    private static final Logger logger = LoggerFactory.getLogger(JdbcGenreDao.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private static final String SELECT_ALL = "SELECT * FROM genre;";

    public JdbcGenreDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public List<Genre> findAll() {
        logger.debug("Get all genres from database");
        return jdbcTemplate.query(SELECT_ALL, GENRE_ROW_MAPPER);
    }

}
