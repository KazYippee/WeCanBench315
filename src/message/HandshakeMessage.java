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
        byte[] headerBytes = HEADER.getBytes(StandardCharsets.UTF_8);
        out.write(headerBytes);
        out.write(new byte[ZERO_BITS_LENGTH]);
        out.writeInt(peerId);
        out.flush();
    }

    public static HandshakeMessage receive(DataInputStream in) throws IOException {
        byte[] headerBytes = new byte[18];
        in.readFully(headerBytes);
        String header = new String(headerBytes, StandardCharsets.UTF_8);
        if (!HEADER.equals(header)) {
            throw new IOException("Invalid handshake header: " + header);
        }
        byte[] zeroBits = new byte[ZERO_BITS_LENGTH];
        in.readFully(zeroBits);
        int peerId = in.readInt();
        return new HandshakeMessage(peerId);
    }
}
