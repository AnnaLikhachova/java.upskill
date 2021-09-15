package com.likhachova.web.controller;

import com.likhachova.model.Movie;
import com.likhachova.service.MovieService;
import com.likhachova.util.MovieRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findAll() {
        logger.debug("Get all movies from database");
        return movieService.findAll();
    }

    @GetMapping(value = "/v1/movie/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findThreeRandom() {
        logger.debug("Get three random movies from database");
        return movieService.findThreeRandom(3);
    }

    @GetMapping(value = "/v1/movie/genre/{genreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> findByGenre(@PathVariable String genreId) {
        logger.debug("Get movies by genre from database");
        return movieService.findByGenre(new MovieRequester.Builder().withGenreId(Integer.parseInt(genreId)).build());
    }

    @GetMapping(value = "/v1/movie/genre/{genreId}/price={sortingOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> sortByPriceOrderByGenre(@PathVariable String genreId, @PathVariable String sortingOrder) {
        logger.debug("Get movies by genre {} and sort them by price {}" + genreId, sortingOrder);
        return movieService.findByGenreSortByPrice(new MovieRequester.Builder().withGenreIdAndSortingOrder(Integer.parseInt(genreId), sortingOrder).build());
    }

    @GetMapping(value = "/v1/movie/genre/{genreId}/rating={sortingOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> sortByRatingOrderByGenre(@PathVariable String genreId, @PathVariable String sortingOrder) {
        logger.debug("Get movies by genre {} and sort them by rating {}" + genreId, sortingOrder);
        return movieService.findByGenreSortByRating(new MovieRequester.Builder().withGenreIdAndSortingOrder(Integer.parseInt(genreId), sortingOrder).build());
    }

    @GetMapping(value = "/v1/movie/price={sortingOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> sortAllMoviesByPrice(@PathVariable String sortingOrder) {
        logger.debug("Sort all movies by price {}" + sortingOrder);
        return movieService.sortByPrice(new MovieRequester.Builder().withSortingOrder(sortingOrder).build());
    }

    @GetMapping(value = "/v1/movie/rating={sortingOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> sortAllMoviesByRating(@PathVariable String sortingOrder) {
        logger.debug("Sort all movies by rating {}" + sortingOrder);
        return movieService.sortByRating(new MovieRequester.Builder().withSortingOrder(sortingOrder).build());
    }

    @GetMapping(value = "/v1/movie/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie findById(@PathVariable String movieId) {
        logger.debug("Get movies by id {} from database", movieId);
        return movieService.findById(new MovieRequester.Builder().withMovieId(Integer.parseInt(movieId)).build());
    }

}
