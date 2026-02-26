package peer;

public class BitField {

    private final byte[] bits;
    private final int numberOfPieces;

    public BitField(int numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
        this.bits = new byte[(int) Math.ceil((double) numberOfPieces / 8)];
    }

    public BitField(int numberOfPieces, boolean complete) {
        this(numberOfPieces);
        if (complete) {
            for (int i = 0; i < numberOfPieces; i++) {
                setPiece(i);
            }
        }
    }

    public BitField(int numberOfPieces, byte[] rawBits) {
        this.numberOfPieces = numberOfPieces;
        this.bits = rawBits.clone();
    }

    public synchronized void setPiece(int index) {
        bits[index / 8] |= (byte) (0x80 >>> (index % 8));
    }

    public synchronized boolean hasPiece(int index) {
        return (bits[index / 8] & (0x80 >>> (index % 8))) != 0;
    }

    public synchronized boolean isComplete() {
        for (int i = 0; i < numberOfPieces; i++) {
            if (!hasPiece(i)) return false;
        }
        return true;
    }

    public synchronized boolean hasInterestingPiece(BitField other) {
        for (int i = 0; i < numberOfPieces; i++) {
            if (other.hasPiece(i) && !hasPiece(i)) return true;
        }
        return false;
    }

    public synchronized byte[] getBytes() {
        return bits.clone();
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public synchronized int getPieceCount() {
        int count = 0;
        for (int i = 0; i < numberOfPieces; i++) {
            if (hasPiece(i)) count++;
        }
        return count;
    }
}
