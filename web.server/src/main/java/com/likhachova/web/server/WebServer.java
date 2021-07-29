package com.likhachova.web.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Collectors;

public class WebServer {

    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        startServer();
    }

    protected static void startServer() throws IOException {

        try(ServerSocket serverSocket = new ServerSocket(PORT);) {
            while(true) {
                try(
                        Socket socket = serverSocket.accept();
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        BufferedWriter bufferedWriter = new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream()))
                ) {

                    String request = bufferedReader.readLine().split(" ")[1];
                    if(request.equals("/")) {
                        String filePath = readFile("index.html");
                        String contentType = "text/html";
                        sendFile(bufferedWriter, filePath, contentType);
                    } else {
                        String fileType = request.substring(request.indexOf("."));
                        if(fileType.equals(".css")){
                            String filePath = readFile(request.substring(1));
                            String contentType = "css/html";
                            sendFile(bufferedWriter, filePath, contentType);
                        } else if (fileType.equals(".js")){
                            String filePath = readFile(request.substring(1));
                            String contentType = "js/html";
                            sendFile(bufferedWriter, filePath, contentType);
                        }
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void sendFile(BufferedWriter bufferedWriter, String path, String contentType) throws Exception {
        bufferedWriter.write("HTTP/1.1 200 OK");
        bufferedWriter.write("Content-Type: " + contentType);
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write(path);
    }

    private static String readFile(String filePath) {
        InputStream in = WebServer.class.getClassLoader()
                .getResourceAsStream(filePath);
        return new BufferedReader(new InputStreamReader(in))
                .lines().collect(Collectors.joining("\n"));
    }

}
