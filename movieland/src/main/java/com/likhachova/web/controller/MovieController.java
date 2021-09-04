package com.likhachova.web.controller;

import com.likhachova.model.Movie;
import com.likhachova.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping(value ="/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findAll(){
        logger.debug("Get all movies from database");
        return movieService.findAll();
    }

    @GetMapping(value ="/v1/movie/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findThreeRandom(){
        logger.debug("Get three random movies from database");
        return  movieService.findThreeRandom(3);
    }

}
