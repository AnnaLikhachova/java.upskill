package com.likhachova.web;

import com.likhachova.web.request.Request;
import com.likhachova.web.request.RequestParser;
import com.likhachova.web.server.Server;
import org.junit.jupiter.api.Test;

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

}