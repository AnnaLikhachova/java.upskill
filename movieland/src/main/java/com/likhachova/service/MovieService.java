package com.likhachova.service;

import com.likhachova.dao.MovieDao;
import com.likhachova.model.Movie;
import com.likhachova.util.MovieRequester;

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

    public Iterable<Movie> findByGenre(MovieRequester movieRequester){
        return movieDao.findByGenre(movieRequester);
    };

    public Iterable<Movie> sortByPrice(MovieRequester movieRequester) {
        return movieDao.sortByPrice(movieRequester);
    }

    public Iterable<Movie> sortByRating(MovieRequester movieRequester) {
        return movieDao.sortByRating(movieRequester);
    }

    public Iterable<Movie> findByGenreSortByPrice(MovieRequester movieRequester) {
        return movieDao.findByGenreSortByPrice(movieRequester);
    }

    public Iterable<Movie> findByGenreSortByRating(MovieRequester movieRequester) {
        return movieDao.findByGenreSortByRating(movieRequester);
    }

    public Movie findById(MovieRequester movieRequester) {
        return movieDao.findById(movieRequester);
    }
}
