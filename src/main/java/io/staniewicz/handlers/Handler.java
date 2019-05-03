package io.staniewicz.handlers;

import java.io.IOException;

public interface Handler<S> {

    public void handle(S s) throws IOException;

}
