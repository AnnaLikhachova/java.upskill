package com.likhachova.dao;

import com.likhachova.model.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> findAll();

}
