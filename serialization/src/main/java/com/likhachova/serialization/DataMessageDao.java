package com.likhachova.serialization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class DataMessageDao implements MessageDao {
    private DataOutputStream objectOutputStream;

    public DataMessageDao(OutputStream outputStream) throws IOException {
        this.objectOutputStream = new DataOutputStream(outputStream);
    }

    @Override
    public void save(Message message) throws IOException {
        double amount = message.getAmount();
        objectOutputStream.writeDouble(amount);
    }

    @Override
    public Message load(Message message) {
        return null;
    }
}
