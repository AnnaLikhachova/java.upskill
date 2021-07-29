package com.likhachova.web.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpMethod {
    GET("GET"),
    POST("POST");

    private final String message;
}
