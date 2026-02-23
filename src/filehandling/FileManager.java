package filehandling;

import java.io.IOException;

public class FileManager {

    private final int peerId;
    private final String fileName;
    private final long fileSize;
    private final int pieceSize;
    private final int numberOfPieces;
    private final String peerDirectory;

    public FileManager(int peerId, String fileName, long fileSize, int pieceSize, int numberOfPieces) {
        this.peerId = peerId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.pieceSize = pieceSize;
        this.numberOfPieces = numberOfPieces;
        this.peerDirectory = "peer_" + peerId;
    }

    public byte[] readPiece(int pieceIndex) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void writePiece(int pieceIndex, byte[] data) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void assembleFile() throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getPieceSizeForIndex(int pieceIndex) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getPeerDirectory() {
        return peerDirectory;
    }
}
