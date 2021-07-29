package com.likhachova.socket;

import com.likhachova.socket.lessons.EchoClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServerTest {

    private EchoClient client;

    @BeforeEach
    public void setup() {
        client = new EchoClient();
        client.startConnection("127.0.0.1", 3000);
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() {
        String responce1 = client.sendMessage("hello");
        String responce2 = client.sendMessage("world");
        String responce3 = client.sendMessage(".");

        assertEquals("echo hello", responce1);
        assertEquals("echo world", responce2);
        assertEquals("end", responce3);
    }

    @AfterEach
    public void tearDown() {
        client.stopConnection();
    }
}
