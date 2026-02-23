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

    public void setPiece(int index) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean hasPiece(int index) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isComplete() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean hasInterestingPiece(BitField other) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public byte[] getBytes() {
        return bits.clone();
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public int getPieceCount() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
