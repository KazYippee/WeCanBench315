package message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HandshakeMessage {

    public static final String HEADER = "P2PFILESHARINGPROJ";
    public static final int ZERO_BITS_LENGTH = 10;
    public static final int LENGTH = 32;

    private final int peerId;

    public HandshakeMessage(int peerId) {
        this.peerId = peerId;
    }

    public int getPeerId() {
        return peerId;
    }

    public void send(DataOutputStream out) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static HandshakeMessage receive(DataInputStream in) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
