package com.likhachova.generator.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QueryResult {
    private boolean isUpdated;
    private int updateCount;
    List<String> columns;
    List<List<Object>> values;
    private List<Product> products = new ArrayList<>();

}
