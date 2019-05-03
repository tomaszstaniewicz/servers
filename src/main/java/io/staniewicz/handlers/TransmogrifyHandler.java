package io.staniewicz.handlers;

import io.staniewicz.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmogrifyHandler implements Handler<Socket> {
    @Override
    public void handle(Socket s) throws IOException {
        try (
                s;
                OutputStream out = s.getOutputStream();
                InputStream in = s.getInputStream();
        ) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(Util.transmogrify(data));
            }
        }
    }
}
