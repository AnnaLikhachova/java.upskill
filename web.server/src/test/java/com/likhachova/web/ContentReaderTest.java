package com.likhachova.web;

import com.likhachova.web.io.ContentReader;
import com.likhachova.web.server.WebServer;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentReaderTest {

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
