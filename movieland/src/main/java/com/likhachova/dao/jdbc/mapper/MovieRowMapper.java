package com.likhachova.dao.jdbc.mapper;

import com.likhachova.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String nameRussian = resultSet.getString("name_russian");
        String nameNative = resultSet.getString("name_native");
        LocalDate yearOfRelease = resultSet.getDate("year_of_release").toLocalDate();
        String description = resultSet.getString("description");
        double rating = resultSet.getDouble("rating");
        double price = resultSet.getDouble("price");
        String picturePath = resultSet.getString("picture_path");
        int votes = resultSet.getInt("votes");

        return Movie.builder()
                .id(id)
                .nameRussian(nameRussian)
                .nameNative(nameNative)
                .yearOfRelease(yearOfRelease)
                .description(description)
                .rating(rating)
                .price(price)
                .picturePath(picturePath)
                .votes(votes)
                .build();
    }
}
