package io.staniewicz.handlers;

import java.io.IOException;

public abstract class DecoratedHandler<S> implements Handler<S> {

    private Handler<S> other;

    public DecoratedHandler(Handler<S> other) {
        this.other = other;
    }

    @Override
    public void handle(S s) throws IOException {
        other.handle(s);
    }
}
