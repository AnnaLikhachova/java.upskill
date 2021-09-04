create table if not exists `movie` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name_russian` varchar(500),
    `name_native` varchar(500),
    `year_of_release` date,
    `description` varchar(1000),
    `rating` double,
    `price` double,
    `picture_path` varchar(500),
    `votes` int
);