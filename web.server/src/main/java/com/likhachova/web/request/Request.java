package com.likhachova.web.request;

import com.likhachova.web.http.HttpMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Request {
    private String uri;
    private Map<String,String> headers = new HashMap<>();
    private HttpMethod method;

}
