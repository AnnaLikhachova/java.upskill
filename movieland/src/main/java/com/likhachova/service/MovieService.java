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
}
