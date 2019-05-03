package io.staniewicz.server;

import io.staniewicz.handlers.Handler;
import io.staniewicz.handlers.PrintingHandler;
import io.staniewicz.handlers.ThreadedHandler;
import io.staniewicz.handlers.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedBlockingServer {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Handler<Socket> handler =
                new ThreadedHandler<>(
                        new PrintingHandler<>(
                                new TransmogrifyHandler()
                        )
                );

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handler.handle(clientSocket);
        }
    }
}
