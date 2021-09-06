package com.likhachova.web.controller;

import com.likhachova.model.Genre;
import com.likhachova.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;

    @GetMapping(value ="/v1/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> findAll(){
        logger.debug("Get all genres from database");
        return genreService.findAll();
    }

}
