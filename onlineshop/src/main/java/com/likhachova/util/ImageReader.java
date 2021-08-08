package com.likhachova.util;

import java.io.*;
import java.util.Base64;

public class ImageReader {

    public static String getImage(String path){
        InputStream inputStream = getFileFromResourceAsStream(path);
        String imgAsBase64 = null;
        try {
            byte[] imgBytes = readFromInputStream(inputStream);
            String encodedBytes = Base64.getEncoder().withoutPadding().encodeToString(imgBytes);
            imgAsBase64 = "data:image/png;base64," + encodedBytes;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return imgAsBase64;
    }

    private static byte[] readFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[8096];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    private static byte[] read(File file) throws IOException {
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            byte[] buffer = new byte[8096];
            outputStream = new ByteArrayOutputStream();
            inputStream = new FileInputStream(file);
            int read = 0;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        }finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    // works for jar
    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = ImageReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File was not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
