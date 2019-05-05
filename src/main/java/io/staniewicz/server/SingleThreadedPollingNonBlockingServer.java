package io.staniewicz.server;

import io.staniewicz.handlers.Handler;
import io.staniewicz.handlers.TransmogrifyChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class SingleThreadedPollingNonBlockingServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);

        Handler<SocketChannel> handler = new TransmogrifyChannelHandler();

        ArrayList<SocketChannel> sockets = new ArrayList<>();

        while (true) {
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                sc.configureBlocking(false);
                sockets.add(sc);
                System.out.println("Connected to: " + sc);
            }
            for (SocketChannel socketChannel : sockets) {
                if (socketChannel.isConnected()) {
                    handler.handle(socketChannel);
                }
            }
            sockets.removeIf(socketChannel -> !socketChannel.isConnected());
        }
    }
}
