package com.likhachova.socket.stream.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3000);
        byte[] buffer = new byte[50];

        InputStream inputStream = socket.getInputStream();

//        new BufferedReader(new InputStreamReader(inputStream)).readLine();
        int count = inputStream.read(buffer);
        System.out.println(new String(buffer, 0, count));
    }
}
