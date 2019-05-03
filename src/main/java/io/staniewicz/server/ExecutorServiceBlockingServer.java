package io.staniewicz.server;

import io.staniewicz.handlers.ExecutorServiceHandler;
import io.staniewicz.handlers.Handler;
import io.staniewicz.handlers.PrintingHandler;
import io.staniewicz.handlers.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutorServiceBlockingServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Handler<Socket> handler =
                new ExecutorServiceHandler<>(
                        new PrintingHandler<>(new TransmogrifyHandler()),
                        Executors.newFixedThreadPool(10),
                        (t, e) -> System.out.println("Uncaught esception " + e + " from thread: " + t)
                );

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handler.handle(clientSocket);
        }
    }
}
