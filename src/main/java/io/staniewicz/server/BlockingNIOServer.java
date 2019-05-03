package io.staniewicz.server;

import io.staniewicz.handlers.BlockingChannelHandler;
import io.staniewicz.handlers.Handler;
import io.staniewicz.handlers.PrintingHandler;
import io.staniewicz.handlers.ThreadedHandler;
import io.staniewicz.handlers.TransmogrifyChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class BlockingNIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8080));

        Handler<SocketChannel> handler =
            new ThreadedHandler<>(
                new PrintingHandler<>(
                    new BlockingChannelHandler(
                        new TransmogrifyChannelHandler()
                    )
                )
            );

        while (true) {
            SocketChannel sc = serverSocket.accept();
            handler.handle(sc);
        }
    }
}
