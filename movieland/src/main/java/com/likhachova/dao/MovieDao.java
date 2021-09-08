package com.likhachova.dao;

import com.likhachova.model.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> findAll();

    List<Movie> findThreeRandom(int limit);

    List<Movie> findByGenre(int genreId);

    List<Movie> sortByPrice(String sortingOrder);

    List<Movie> sortByRating(String sortingOrder);

    List<Movie> findByGenreSortByPrice(int parseInt, String sortingOrder);

    List<Movie> findByGenreSortByRating(int parseInt, String sortingOrder);
}
