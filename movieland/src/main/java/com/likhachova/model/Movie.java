package com.likhachova.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.likhachova.util.LocalDateDeserializer;
import com.likhachova.util.LocalDateSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private int votes;

}
