package com.likhachova.socket;

import com.likhachova.socket.lessons.Client;
import com.likhachova.socket.lessons.ClientIO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocketTest {

    @Test
    public void givenClientIO_whenServerRespondsWhenStarted_thenCorrectMessageIsReturned() throws IOException {
        ClientIO clientIO = new ClientIO();
        clientIO.startConnection("127.0.0.1", 3000);
        String response = clientIO.sendMessageToServer("echo");
        clientIO.stopConnection();
        assertEquals("echo client", response);
    }

    @Test
    public void givenClient_whenServerRespondsWhenStarted_thenCorrectMessageIsReturned() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 3000);
        String response = client.sendMessage("echo");
        client.stopConnection();
        assertEquals("echo client", response);
    }
}
