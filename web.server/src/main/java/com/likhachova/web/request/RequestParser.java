package com.likhachova.web.request;

import com.likhachova.web.http.HttpMethod;
import org.springframework.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class RequestParser {

    public Request parseRequest(BufferedReader bufferedReader) throws IOException {
        Request request = new Request();
        injectUriAndMethod(bufferedReader, request);
        return request;
    }

    public void injectUriAndMethod(BufferedReader bufferedReader, Request request) throws IOException {
        String line;
        while(!(line = bufferedReader.readLine()).isEmpty()) {
            if(line.contains("HTTP")) {
                String[] requestDetails = line.split(" ");
                request.setMethod(HttpMethod.valueOf(requestDetails[0]));
                request.setUri(requestDetails[1]);
            }
            if(line.contains("Accept")) {
                injectHeaders(request, line);
            }
        }
    }

    public void injectHeaders(Request request, String headersLine) {
        Map<String, String> headers = request.getHeaders();
        String[] requestDetails = headersLine.substring(headersLine.indexOf(":")).split(",");
        for(String header : requestDetails) {
            headers.put(HttpHeaders.CONTENT_TYPE, header);
        }
    }
}
