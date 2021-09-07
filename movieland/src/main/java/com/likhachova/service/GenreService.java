package com.likhachova.service;

import com.likhachova.dao.GenreDao;
import com.likhachova.model.Genre;
import org.springframework.cache.annotation.Cacheable;

public class GenreService {

    private GenreDao genreDao;

    public GenreService(GenreDao genreDao)
    {
        this.genreDao = genreDao;
    }

    @Cacheable(cacheNames = "genres")
    public Iterable<Genre> findAll() {
        return genreDao.findAll();
    }

}
