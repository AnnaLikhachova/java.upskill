package com.likhachova.socket.stream.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIO {

    private static final Logger LOG = LoggerFactory.getLogger(ServerIO.class);

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedOutputStream out;
    private BufferedInputStream in;

    public static void main(String[] args) throws IOException {

        ServerIO serverIO = new ServerIO();
        try {
            serverIO.start(3000);
        }
        catch(IOException e) {
            LOG.debug("log start server" + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new BufferedOutputStream(clientSocket.getOutputStream());
        in = new BufferedInputStream(clientSocket.getInputStream());

        byte[] buffer = new byte[100];
        int bytesRead = in.read(buffer);

        String content = new String(buffer, 0, bytesRead);

        if("echo".equals(content)) {
            out.write(("echo: " + new String(buffer, 0, bytesRead)).getBytes());
        }
        else {
            out.write(("error occurred: " + new String(buffer, 0, bytesRead)).getBytes());
        }
        stop();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
