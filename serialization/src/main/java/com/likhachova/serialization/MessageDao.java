package com.likhachova.serialization;

import java.io.IOException;

public interface MessageDao {
    void save(Message message) throws IOException;

    Message load(Message message);
}
