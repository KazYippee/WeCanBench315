package message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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
        int length = 1 + payload.length;
        out.writeInt(length);
        out.writeByte(type.getValue());
        if (payload.length > 0) {
            out.write(payload);
        }
        out.flush();
    }

    public static Message receive(DataInputStream in) throws IOException {
        int length = in.readInt();
        byte typeByte = in.readByte();
        MessageType type = MessageType.fromValue(typeByte);
        byte[] payload = new byte[length - 1];
        if (payload.length > 0) {
            in.readFully(payload);
        }
        return new Message(type, payload);
    }

    public static Message buildHave(int pieceIndex) {
        byte[] payload = ByteBuffer.allocate(4).putInt(pieceIndex).array();
        return new Message(MessageType.HAVE, payload);
    }

    public static Message buildBitfield(byte[] bitfieldBytes) {
        return new Message(MessageType.BITFIELD, bitfieldBytes);
    }

    public static Message buildRequest(int pieceIndex) {
        byte[] payload = ByteBuffer.allocate(4).putInt(pieceIndex).array();
        return new Message(MessageType.REQUEST, payload);
    }

    public static Message buildPiece(int pieceIndex, byte[] data) {
        byte[] payload = new byte[4 + data.length];
        ByteBuffer.wrap(payload).putInt(pieceIndex);
        System.arraycopy(data, 0, payload, 4, data.length);
        return new Message(MessageType.PIECE, payload);
    }

    public int parsePieceIndex() {
        return ByteBuffer.wrap(payload, 0, 4).getInt();
    }

    public byte[] parsePieceData() {
        byte[] data = new byte[payload.length - 4];
        System.arraycopy(payload, 4, data, 0, data.length);
        return data;
    }
}
