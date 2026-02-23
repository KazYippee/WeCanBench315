package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PeerLogger {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final BufferedWriter writer;
    private final int localPeerId;

    public PeerLogger(int localPeerId) throws IOException {
        this.localPeerId = localPeerId;
        this.writer = new BufferedWriter(new FileWriter("log_peer_" + localPeerId + ".log", true));
    }

    private String timestamp() {
        return "[" + LocalDateTime.now().format(FORMATTER) + "]";
    }

    private void write(String message) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logTCPConnectionTo(int remotePeerId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logTCPConnectionFrom(int remotePeerId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logPreferredNeighbors(List<Integer> neighborIds) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logOptimisticallyUnchokedNeighbor(int neighborId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logUnchoked(int byPeerId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logChoked(int byPeerId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logHaveMessage(int fromPeerId, int pieceIndex) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logInterestedMessage(int fromPeerId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logNotInterestedMessage(int fromPeerId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logDownloadedPiece(int pieceIndex, int fromPeerId, int totalPieces) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logCompletedDownload() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void close() throws IOException {
        writer.close();
    }
}
