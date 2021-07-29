package com.likhachova.web.handler;

import com.likhachova.web.http.HttpStatus;
import com.likhachova.web.io.ContentReader;
import com.likhachova.web.io.ResponseWriter;
import com.likhachova.web.request.Request;
import com.likhachova.web.request.RequestParser;
import com.likhachova.web.response.Response;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

@AllArgsConstructor
public class RequestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

    private String webAppPath;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void handle() throws IOException {
        try {
            RequestParser requestParser = new RequestParser();
            Request request = requestParser.parseRequest(bufferedReader);
            Response response = new Response();
            ContentReader contentReader = new ContentReader(webAppPath);
            response.setContentReader(contentReader);
            response.setHttpStatus(HttpStatus.OK);
            ResponseWriter.writeResponse(request, bufferedWriter, response);
        }
        catch(IllegalArgumentException e) {
            LOG.error("Log client error" + e.getMessage());
            ResponseWriter.writeErrorResponse(bufferedWriter, HttpStatus.BAD_REQUEST);
        }
        catch(IOException e) {
            try {
                ResponseWriter.writeErrorResponse(bufferedWriter, HttpStatus.NOT_FOUND);
            }
            catch(IOException exception) {
                LOG.error("Log server error" + exception.getMessage());
                ResponseWriter.writeErrorResponse(bufferedWriter, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            LOG.error("Log client error" + e.getMessage());
        }
    }
}
