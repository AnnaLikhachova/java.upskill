package com.likhachova.web.io;

import com.likhachova.web.server.WebServer;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ContentReader {

    private String pathToWebApp;

    public ContentReader(String pathToWebApp){
        this.pathToWebApp = pathToWebApp;
    }

    public String readContent(String uri) {
        String filePath = "";
        if(uri.equals("/")) {
            filePath = readFile("index.html");
        } else {

            filePath = readFile(uri.substring(1));
        }
        return filePath;
    }

    public String readFile(String pathToWebApp) {
        InputStream in = WebServer.class.getClassLoader()
                .getResourceAsStream(pathToWebApp);
        return new BufferedReader(new InputStreamReader(in))
                .lines().collect(Collectors.joining("\n"));
    }
}
