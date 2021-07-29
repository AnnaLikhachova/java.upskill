package com.likhachova.serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class SerializationMessageDao implements MessageDao {
    private ObjectOutputStream objectOutputStream;

    public SerializationMessageDao(OutputStream outputStream) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(outputStream);
    }

    @Override
    public void save(Message message) throws IOException {
        objectOutputStream.writeObject(message);
    }

    @Override
    public Message load(Message message) {
        return null;
    }
}
