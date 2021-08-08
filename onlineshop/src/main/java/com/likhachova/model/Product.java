package com.likhachova.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private int price;
    private LocalDate date;


    public Product(String name, int price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
