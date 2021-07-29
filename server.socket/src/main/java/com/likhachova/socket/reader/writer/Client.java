package com.likhachova.socket.reader.writer;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final int PORT = 3000;

    public static void main(String[] args) {
        System.out.println(sendMessageToEchoServer("Hello 2"));
    }

    protected static String sendMessageToEchoServer(String message) {

        try (Socket socket = new Socket("localhost", PORT);
             BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bufferedWriter
                     = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            bufferedWriter.write(message);
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
