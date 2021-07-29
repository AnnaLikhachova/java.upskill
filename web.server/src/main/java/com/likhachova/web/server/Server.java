package com.likhachova.web.server;

import com.likhachova.web.handler.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Server {

    private static final Logger LOG = LoggerFactory.getLogger(Server.class);

    private int port;
    private String webAppPath;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String path) {
        this.webAppPath = path;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
        }
        catch(IOException exception) {
            LOG.error("Log socket failed" + exception.getMessage());
            throw new RuntimeException(exception);
        }

        while(true) {
            try(
                    Socket socket = serverSocket.accept();
                    BufferedReader bufferedReader
                            = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter bufferedWriter
                            = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                RequestHandler requestHandler = new RequestHandler(webAppPath, bufferedReader, bufferedWriter);
                requestHandler.handle();
            }
            catch(IOException exception) {
                LOG.error("Log start server failed" + exception.getMessage());
                throw new RuntimeException(exception);
            }
        }
    }

    public void stop() {
        try {
            bufferedWriter.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();
        }
        catch(IOException e) {
            LOG.debug("Log when stop server" + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
