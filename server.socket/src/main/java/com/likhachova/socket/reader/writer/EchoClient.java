package com.likhachova.socket.reader.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

    private static final Logger LOG = LoggerFactory.getLogger(EchoClient.class);

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch(IOException e) {
            LOG.debug("log when start connection from client" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String sendMessage(String message) {
        try {
            out.println(message);
            return in.readLine();
        }
        catch(Exception e) {
            LOG.debug("log when send message from client" + e.getMessage());
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        }
        catch(IOException e) {
            LOG.debug("log when stop connection from client" + e.getMessage());
        }
    }
}
