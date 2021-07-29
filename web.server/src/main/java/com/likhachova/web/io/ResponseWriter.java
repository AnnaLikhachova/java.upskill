package com.likhachova.web.io;

import com.likhachova.web.http.HttpStatus;
import com.likhachova.web.request.Request;
import com.likhachova.web.response.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {

    public static void writeResponse(Request request, BufferedWriter bufferedWriter, Response response) throws IOException {
        bufferedWriter.write("HTTP/1.1"+ response.getHttpStatus());
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write(response.getContentReader().readContent(request.getUri()));
    }

    public static void writeErrorResponse(BufferedWriter bufferedWriter, HttpStatus httpStatus) throws IOException {
        bufferedWriter.write("HTTP/1.1"+ httpStatus);
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write(httpStatus.getMessage());
    }
}
