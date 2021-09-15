package com.likhachova.dao.jdbc;

import com.likhachova.model.Movie;
import com.likhachova.util.MovieRequester;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JdbcMovieDaoTest {

    @Mock
    JdbcMovieDao jdbcMovieDao;

    @BeforeEach
    public void before() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:~/movies1;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        assertNotNull(dataSource);
        jdbcMovieDao = new JdbcMovieDao(dataSource);
    }

    @Test
    public void test_MockCreation(){
        assertNotNull(jdbcMovieDao);
    }

    @Test
    @DisplayName("Get all movies")
    public void whenMockJdbcMovieDao_thenReturnCorrectMoviesCount() {
        List<Movie> movies = jdbcMovieDao.findAll();
        assertNotNull(movies);
        assertEquals(25, movies.size());
    }

    @Test
    @DisplayName("Get three random movies")
    public void whenMockJdbcMovieDao_thenReturnTreeRandomMovies() {
        List<Movie> movies = jdbcMovieDao.findThreeRandom(3);
        assertEquals(3, movies.size());
    }

    @Test
    @DisplayName("Get movies by genre")
    public void whenMockJdbcMovieDao_thenReturnMoviesByGenre() {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withGenreId(1).build();
        List<Movie> movies = jdbcMovieDao.findByGenre(movieRequester);
        assertEquals(movie.getId(), movies.get(0).getId());
        assertEquals(movie.getNameNative(), movies.get(0).getNameNative());
        assertEquals(movie.getNameRussian(), movies.get(0).getNameRussian());
        assertEquals(movie.getDescription(), movies.get(0).getDescription());
        assertEquals(movie.getPrice(), movies.get(0).getPrice());
        assertEquals(movie.getVotes(), movies.get(0).getVotes());
        assertEquals(movie.getYearOfRelease(), movies.get(0).getYearOfRelease());
        assertEquals(movie.getPicturePath(), movies.get(0).getPicturePath());
        assertEquals(movie.getRating(), movies.get(0).getRating());
    }

    @Test
    @DisplayName("Sort movies by price")
    public void whenMockJdbcMovieDao_thenReturnMoviesAreSortedByPrice() {
        Movie movie = Movie.builder()
                .id(3)
                .nameRussian("Форрест Гамп")
                .nameNative("Forrest Gump")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("От лица главного героя Форреста Гампа, слабоумного безобидного человека с благородным и открытым сердцем, рассказывается история его необыкновенной жизни.Фантастическим образом превращается он в известного футболиста, героя войны, преуспевающего бизнесмена. Он становится миллиардером, но остается таким же бесхитростным, глупым и добрым. Форреста ждет постоянный успех во всем, а он любит девочку, с которой дружил в детстве, но взаимность приходит слишком поздно.")
                .rating(8.6)
                .price(200.6)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withSortingOrder("desc").build();
        List<Movie> movies = jdbcMovieDao.sortByPrice( movieRequester);
        assertEquals(movie.getId(), movies.get(0).getId());
        assertEquals(movie.getNameNative(), movies.get(0).getNameNative());
        assertEquals(movie.getNameRussian(), movies.get(0).getNameRussian());
        assertEquals(movie.getDescription(), movies.get(0).getDescription());
        assertEquals(movie.getPrice(), movies.get(0).getPrice());
        assertEquals(movie.getVotes(), movies.get(0).getVotes());
        assertEquals(movie.getYearOfRelease(), movies.get(0).getYearOfRelease());
        assertEquals(movie.getPicturePath(), movies.get(0).getPicturePath());
        assertEquals(movie.getRating(), movies.get(0).getRating());
    }

    @Test
    @DisplayName("Sort movies by rating")
    public void whenMockJdbcMovieDao_thenReturnMoviesAreSortedByrating() {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withSortingOrder("desc").build();
        List<Movie> movies = jdbcMovieDao.sortByRating( movieRequester);
        assertEquals(movie.getId(), movies.get(0).getId());
        assertEquals(movie.getNameNative(), movies.get(0).getNameNative());
        assertEquals(movie.getNameRussian(), movies.get(0).getNameRussian());
        assertEquals(movie.getDescription(), movies.get(0).getDescription());
        assertEquals(movie.getPrice(), movies.get(0).getPrice());
        assertEquals(movie.getVotes(), movies.get(0).getVotes());
        assertEquals(movie.getYearOfRelease(), movies.get(0).getYearOfRelease());
        assertEquals(movie.getPicturePath(), movies.get(0).getPicturePath());
        assertEquals(movie.getRating(), movies.get(0).getRating());
    }

    @Test
    @DisplayName("Get movies by genre and sort by rating")
    public void whenMockJdbcMovieDao_thenReturnMoviesByGenreAndSortedByRating() {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withGenreIdAndSortingOrder(1, "desc").build();
        List<Movie> movies = jdbcMovieDao.findByGenreSortByRating(movieRequester);
        assertEquals(movie.getId(), movies.get(0).getId());
        assertEquals(movie.getNameNative(), movies.get(0).getNameNative());
        assertEquals(movie.getNameRussian(), movies.get(0).getNameRussian());
        assertEquals(movie.getDescription(), movies.get(0).getDescription());
        assertEquals(movie.getPrice(), movies.get(0).getPrice());
        assertEquals(movie.getVotes(), movies.get(0).getVotes());
        assertEquals(movie.getYearOfRelease(), movies.get(0).getYearOfRelease());
        assertEquals(movie.getPicturePath(), movies.get(0).getPicturePath());
        assertEquals(movie.getRating(), movies.get(0).getRating());
    }

    @Test
    @DisplayName("Get movies by genre and sort by rating")
    public void whenMockJdbcMovieDao_thenReturnMoviesByGenreAndSortedByPrice() {
        Movie movie = Movie.builder()
                .id(3)
                .nameRussian("Форрест Гамп")
                .nameNative("Forrest Gump")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("От лица главного героя Форреста Гампа, слабоумного безобидного человека с благородным и открытым сердцем, рассказывается история его необыкновенной жизни.Фантастическим образом превращается он в известного футболиста, героя войны, преуспевающего бизнесмена. Он становится миллиардером, но остается таким же бесхитростным, глупым и добрым. Форреста ждет постоянный успех во всем, а он любит девочку, с которой дружил в детстве, но взаимность приходит слишком поздно.")
                .rating(8.6)
                .price(200.6)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withGenreIdAndSortingOrder(1, "desc").build();
        List<Movie> movies = jdbcMovieDao.findByGenreSortByPrice(movieRequester);
        assertEquals(movie.getId(), movies.get(0).getId());
        assertEquals(movie.getNameNative(), movies.get(0).getNameNative());
        assertEquals(movie.getNameRussian(), movies.get(0).getNameRussian());
        assertEquals(movie.getDescription(), movies.get(0).getDescription());
        assertEquals(movie.getPrice(), movies.get(0).getPrice());
        assertEquals(movie.getVotes(), movies.get(0).getVotes());
        assertEquals(movie.getYearOfRelease(), movies.get(0).getYearOfRelease());
        assertEquals(movie.getPicturePath(), movies.get(0).getPicturePath());
        assertEquals(movie.getRating(), movies.get(0).getRating());
    }

    @Test
    @DisplayName("Get movie by id")
    public void whenMockJdbcMovieDao_thenReturnMovieById() {
        Movie movieExpected = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-d")))
                .description("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")
                .rating(8.9)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .votes(100)
                .build();
        MovieRequester movieRequester = new MovieRequester.Builder().withMovieId(1).build();
        Movie movie = jdbcMovieDao.findById(movieRequester);
        assertEquals(movieExpected.getId(), movie.getId());
        assertEquals(movieExpected.getNameNative(), movie.getNameNative());
        assertEquals(movieExpected.getNameRussian(), movie.getNameRussian());
        assertEquals(movieExpected.getDescription(), movie.getDescription());
        assertEquals(movieExpected.getPrice(), movie.getPrice());
        assertEquals(movieExpected.getVotes(), movie.getVotes());
        assertEquals(movieExpected.getYearOfRelease(), movie.getYearOfRelease());
        assertEquals(movieExpected.getPicturePath(), movie.getPicturePath());
        assertEquals(movieExpected.getRating(), movie.getRating());
    }
}
