package com.likhachova.service;

import com.likhachova.dao.MovieDao;
import com.likhachova.model.Movie;

public class MovieService {

    private MovieDao movieDao;

    public MovieService(MovieDao movieDao)
    {
        this.movieDao = movieDao;
    }

    public Iterable<Movie> findAll() {
        return movieDao.findAll();
    }

    public Iterable<Movie> findThreeRandom(int limit) {
        return movieDao.findThreeRandom(limit);
    }

    public Iterable<Movie> findByGenre(int genreId){
        return movieDao.findByGenre(genreId);
    };

    public Iterable<Movie> sortByPrice(String sortingOrder) {
        return movieDao.sortByPrice(sortingOrder);
    }

    public Iterable<Movie> sortByRating(String sortingOrder) {
        return movieDao.sortByRating(sortingOrder);
    }

    public Iterable<Movie> findByGenreSortByPrice(int genreId, String sortingOrder) {
        return movieDao.findByGenreSortByPrice(genreId, sortingOrder);
    }

    public Iterable<Movie> findByGenreSortByRating(int parseInt, String sortingOrder) {
        return movieDao.findByGenreSortByRating(parseInt, sortingOrder);
    }
}
