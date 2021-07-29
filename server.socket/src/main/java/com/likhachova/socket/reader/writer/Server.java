package com.likhachova.socket.reader.writer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) {
        startEchoServer();
    }

    protected static void startEchoServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        while (true) {
            try (
                    Socket socket = serverSocket.accept();
                    BufferedReader bufferedReader
                         = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter bufferedWriter
                         = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

                String line = bufferedReader.readLine();
                bufferedWriter.write(("Echo: " + line));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
