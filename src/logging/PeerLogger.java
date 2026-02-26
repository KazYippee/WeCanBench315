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

    private synchronized void write(String message) {
        try {
            writer.write(timestamp() + " " + message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Logger error: " + e.getMessage());
        }
    }

    public void logTCPConnectionTo(int remotePeerId) {
        write("Peer " + localPeerId + " makes a connection to Peer " + remotePeerId + ".");
    }

    public void logTCPConnectionFrom(int remotePeerId) {
        write("Peer " + localPeerId + " is connected from Peer " + remotePeerId + ".");
    }

    public void logPreferredNeighbors(List<Integer> neighborIds) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < neighborIds.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(neighborIds.get(i));
        }
        write("Peer " + localPeerId + " has the preferred neighbors [" + sb + "].");
    }

    public void logOptimisticallyUnchokedNeighbor(int neighborId) {
        write("Peer " + localPeerId + " has the optimistically unchoked neighbor " + neighborId + ".");
    }

    public void logUnchoked(int byPeerId) {
        write("Peer " + localPeerId + " is unchoked by " + byPeerId + ".");
    }

    public void logChoked(int byPeerId) {
        write("Peer " + localPeerId + " is choked by " + byPeerId + ".");
    }

    public void logHaveMessage(int fromPeerId, int pieceIndex) {
        write("Peer " + localPeerId + " received the 'have' message from " + fromPeerId + " for the piece " + pieceIndex + ".");
    }

    public void logInterestedMessage(int fromPeerId) {
        write("Peer " + localPeerId + " received the 'interested' message from " + fromPeerId + ".");
    }

    public void logNotInterestedMessage(int fromPeerId) {
        write("Peer " + localPeerId + " received the 'not interested' message from " + fromPeerId + ".");
    }

    public void logDownloadedPiece(int pieceIndex, int fromPeerId, int totalPieces) {
        write("Peer " + localPeerId + " has downloaded the piece " + pieceIndex + " from " + fromPeerId + ". Now the number of pieces it has is " + totalPieces + ".");
    }

    public void logCompletedDownload() {
        write("Peer " + localPeerId + " has downloaded the complete file.");
    }

    public void close() throws IOException {
        writer.close();
    }
}
