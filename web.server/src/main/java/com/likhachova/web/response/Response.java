package com.likhachova.web.response;

import com.likhachova.web.http.HttpMethod;
import com.likhachova.web.http.HttpStatus;
import com.likhachova.web.io.ContentReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private HttpStatus httpStatus;
    private ContentReader contentReader;

    public HttpMethod method(HttpMethod type){
        return null;
    }
}
