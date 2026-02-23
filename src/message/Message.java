package message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Message {

    private final MessageType type;
    private final byte[] payload;

    public Message(MessageType type, byte[] payload) {
        this.type = type;
        this.payload = payload;
    }

    public Message(MessageType type) {
        this(type, new byte[0]);
    }

    public MessageType getType() {
        return type;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void send(DataOutputStream out) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static Message receive(DataInputStream in) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
