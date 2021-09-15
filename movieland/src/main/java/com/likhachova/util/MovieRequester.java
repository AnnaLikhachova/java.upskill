package com.likhachova.util;

import lombok.Getter;

// Pattern builder;
@Getter
public class MovieRequester {

    private int genreId;
    private String sortingOrder;
    private int movieId;

    public static class Builder {
        private MovieRequester movieRequester;

        public Builder() {
            movieRequester = new MovieRequester();
        }

        public Builder withGenreId(int genreId) {
            movieRequester.genreId = genreId;
            return this;
        }

        public Builder withSortingOrder(String sortingOrder) {
            movieRequester.sortingOrder = sortingOrder;
            return this;
        }

        public Builder withGenreIdAndSortingOrder(int genreId, String sortingOrder) {
            movieRequester.genreId = genreId;
            movieRequester.sortingOrder = sortingOrder;
            return this;
        }

        public Builder withMovieId(int movieId) {
            movieRequester.movieId = movieId;
            return this;
        }

        public MovieRequester build() {
            return movieRequester;
        }
    }
}
