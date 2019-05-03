package io.staniewicz.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handle(clientSocket);
        }
    }

    private static void handle(Socket clientSocket) throws IOException {
        try (
            clientSocket;
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
        ) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(Character.toUpperCase(data));
            }
        }
    }
}
