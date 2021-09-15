package com.likhachova.dao;

import com.likhachova.model.Movie;
import com.likhachova.util.MovieRequester;

import java.util.List;

public interface MovieDao {

    List<Movie> findAll();

    List<Movie> findThreeRandom(int limit);

    List<Movie> findByGenre(MovieRequester movieRequester);

    List<Movie> sortByPrice(MovieRequester movieRequester);

    List<Movie> sortByRating(MovieRequester movieRequester);

    List<Movie> findByGenreSortByPrice(MovieRequester movieRequester);

    List<Movie> findByGenreSortByRating(MovieRequester movieRequester);

    Movie findById(MovieRequester movieRequester);
}
