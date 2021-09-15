package com.likhachova.web;

import com.likhachova.web.request.Request;
import com.likhachova.web.request.RequestParser;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {

    @Test
    public void injectUriAndMethod() throws IOException {
        RequestParser requestParser = new RequestParser();
        String uri = "/home.html";
        String method = "GET";
        String toRead = "GET /home.html HTTP/1.1"+ "\n"+ "\n";
        BufferedReader bufferedReader = new BufferedReader( new StringReader(toRead));
        Request request = new Request();
        requestParser.injectUriAndMethod(bufferedReader,request);
        assertEquals(uri, request.getUri());
        assertEquals(method, request.getMethod().getMessage());
    }
}
