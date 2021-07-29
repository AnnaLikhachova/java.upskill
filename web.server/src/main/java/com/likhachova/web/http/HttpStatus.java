package com.likhachova.web.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    BAD_REQUEST(400, "BAD_REQUEST");

    private final int code;
    private final String message;

}
