package com.likhachova.service;

import com.likhachova.dao.GenreDao;
import com.likhachova.model.Genre;

public class GenreService {

    private GenreDao genreDao;

    public GenreService(GenreDao genreDao)
    {
        this.genreDao = genreDao;
    }

    public Iterable<Genre> findAll() {
        return genreDao.findAll();
    }

}
