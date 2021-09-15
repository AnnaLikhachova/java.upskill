package com.likhachova.util;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.likhachova.dao.GenreDao;
import com.likhachova.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GenreCache {

    private static final Logger logger = LoggerFactory.getLogger(GenreCache.class);

    private static GenreCache genreCache = new GenreCache();

    private static Map<String, Genre> db = new ConcurrentHashMap<>();

    public static GenreCache getInstance() {
        return genreCache;
    }

    private LoadingCache<String, Genre> cache;

    @Autowired
    private GenreDao genreDao;

    public void checkCache(){
        if(db.size() == 0){
            List<Genre> genreList = genreDao.findAll();
            for(Genre genre: genreList){
                db.put(String.valueOf(genre.getId()),genre);
            }
            logger.debug("Put all genres to cache");
        }
    }

    private GenreCache() {
        cache = CacheBuilder.newBuilder().refreshAfterWrite(4, TimeUnit.HOURS).
                build(new CacheLoader<String, Genre>() {

                          @Override
                          public Genre load(String id) {
                              return getGenre(id);
                          }
                      }
                );
    }

    private Genre getGenre(String id) {
        logger.debug("Get genre from cache by id {}", id);
        return db.get(id);
    }

}