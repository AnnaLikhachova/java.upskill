package com.likhachova.socket.stream.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        // listen
        Socket socket = serverSocket.accept();

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("Hello client!".getBytes());
    }

}
