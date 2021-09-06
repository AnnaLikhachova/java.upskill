create table if not exists `movie_genre` (
    `movie_id` int NOT NULL,
    `genre_id`  int NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    CONSTRAINT FK_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT FK_GENRE FOREIGN KEY (genre_id) REFERENCES genre (id)
);

