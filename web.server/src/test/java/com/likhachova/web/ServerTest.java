package com.likhachova.web;

import com.likhachova.web.io.ContentReader;
import com.likhachova.web.request.Request;
import com.likhachova.web.request.RequestParser;
import com.likhachova.web.server.Server;
import com.likhachova.web.server.WebServer;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServerTest {

    @Test
    public void whenServerStartsThenClientRecievesContent() {
        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("src/main/resources");
        server.start();
        assertNotNull(server);
    }
    @Test
    public void whenServerIsSetWithSpecificPortThenServerGetsCreatedWithThisPort() {
        int testPort = 9000;
        Server server = new Server();
        server.setPort(testPort);
        assertEquals(server.getPort(), testPort);
    }

    @Test
    public void injectHeaders() {
        RequestParser requestParser = new RequestParser();
        Request request = new Request();
        String headersLine = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
        requestParser.injectHeaders(request,headersLine);
        assertFalse(request.getHeaders().isEmpty());
    }

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

    @Test
    public void whenContentReaderReadsFileThenFileIsRead() {
        String pathToWebApp = "css/styles.css";
        ContentReader contentReader = new ContentReader();
        InputStream in = WebServer.class.getClassLoader()
                .getResourceAsStream(pathToWebApp);
        String expected = new BufferedReader(new InputStreamReader(in))
                .lines().collect(Collectors.joining("\n"));
        assertEquals(expected, contentReader.readFile(pathToWebApp));
    }

    @Test
    public void whenContentReaderReadsContentThenContentIsRead() {
        ContentReader contentReader = new ContentReader();
        String uri = "/css/styles.css";
        InputStream in = WebServer.class.getClassLoader()
                .getResourceAsStream(uri.substring(1));
        String expected = new BufferedReader(new InputStreamReader(in))
                .lines().collect(Collectors.joining("\n"));
        assertEquals(expected, contentReader.readContent(uri));
    }

}