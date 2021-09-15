package com.likhachova.socket.lessons;

import java.io.*;
import java.net.Socket;

public class ClientIO {
    private Socket clientSocket;
    private BufferedOutputStream out;
    private BufferedInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new BufferedOutputStream(clientSocket.getOutputStream());
        in = new BufferedInputStream(clientSocket.getInputStream());
    }

    public String sendMessageToServer(String str) throws IOException {

        byte[] buffer = new byte[100];
        out.write(str.getBytes());
        out.flush();

        int count;
        String content = "";
        while((count = in.read(buffer)) != -1) {
            content += new String(buffer, 0, count);
        }

        return content;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
