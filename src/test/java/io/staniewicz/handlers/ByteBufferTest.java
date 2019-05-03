package io.staniewicz.handlers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

class ByteBufferTest {

    @Test
    void testByteBuffer() {

        ByteBuffer buffer = ByteBuffer.allocate(24);
        buffer.put((byte) 10);

        assertEquals(buffer.position(), 1);
        assertEquals(buffer.limit(), 24);

        buffer.flip();

        assertEquals(buffer.position(), 0);
        assertEquals(buffer.limit(), 1);

        buffer.clear();

        assertEquals(buffer.position(), 0);
        assertEquals(buffer.limit(), 24);

        buffer.put((byte) 10);
        buffer.put((byte) 20);
        buffer.put((byte) 30);
        buffer.flip();

        assertEquals(10, buffer.get());
        assertEquals(1, buffer.position());
        assertEquals(20, buffer.get());
        assertEquals(2, buffer.position());

        buffer.rewind();
        assertEquals(10, buffer.get());

        buffer.compact();
        assertEquals(2, buffer.position());

        buffer.flip();
        assertEquals(20, buffer.get());
        assertEquals(30, buffer.get());
    }
}