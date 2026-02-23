package message;

public enum MessageType {

    CHOKE((byte) 0),
    UNCHOKE((byte) 1),
    INTERESTED((byte) 2),
    NOT_INTERESTED((byte) 3),
    HAVE((byte) 4),
    BITFIELD((byte) 5),
    REQUEST((byte) 6),
    PIECE((byte) 7);

    private final byte value;

    MessageType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static MessageType fromValue(byte value) {
        for (MessageType type : values()) {
            if (type.value == value) return type;
        }
        throw new IllegalArgumentException("Unknown message type: " + value);
    }
}
