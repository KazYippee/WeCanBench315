package peer;

import config.CommonConfig;
import config.PeerInfoConfig;
import filehandling.FileManager;
import logging.PeerLogger;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PeerManager {

    private final int localPeerId;
    private final BitField localBitField;
    private final Map<Integer, PeerConnection> connections;
    private final Map<Integer, BitField> neighborBitFields;
    private final Set<Integer> requestedPieces;
    private volatile boolean allPeersFinished;
    private FileManager fileManager;
    private PeerLogger logger;

    public PeerManager(int localPeerId, boolean hasFile) {
        this.localPeerId = localPeerId;
        int numPieces = CommonConfig.getInstance().getNumberOfPieces();
        this.localBitField = new BitField(numPieces, hasFile);
        this.connections = new ConcurrentHashMap<>();
        this.neighborBitFields = new ConcurrentHashMap<>();
        this.requestedPieces = ConcurrentHashMap.newKeySet();
        this.allPeersFinished = false;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setLogger(PeerLogger logger) {
        this.logger = logger;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public PeerLogger getLogger() {
        return logger;
    }

    public void start() {
    }

    public void addConnection(int remotePeerId, PeerConnection connection) {
        connections.put(remotePeerId, connection);
        neighborBitFields.put(remotePeerId, new BitField(CommonConfig.getInstance().getNumberOfPieces()));
    }

    public void updateNeighborBitField(int remotePeerId, BitField bitField) {
        neighborBitFields.put(remotePeerId, bitField);
    }

    public synchronized void setNeighborHasPiece(int remotePeerId, int pieceIndex) {
        BitField bf = neighborBitFields.get(remotePeerId);
        if (bf != null) {
            bf.setPiece(pieceIndex);
        }
    }

    public synchronized boolean checkAllFinished() {
        if (!localBitField.isComplete()) return false;
        for (BitField bf : neighborBitFields.values()) {
            if (!bf.isComplete()) return false;
        }
        int totalPeers = PeerInfoConfig.getInstance().getPeers().size();
        if (neighborBitFields.size() < totalPeers - 1) return false;
        allPeersFinished = true;
        return true;
    }

    public boolean addRequestedPiece(int pieceIndex) {
        return requestedPieces.add(pieceIndex);
    }

    public void removeRequestedPiece(int pieceIndex) {
        requestedPieces.remove(pieceIndex);
    }

    public boolean isRequested(int pieceIndex) {
        return requestedPieces.contains(pieceIndex);
    }

    public int getLocalPeerId() {
        return localPeerId;
    }

    public BitField getLocalBitField() {
        return localBitField;
    }

    public Map<Integer, PeerConnection> getConnections() {
        return connections;
    }

    public Map<Integer, BitField> getNeighborBitFields() {
        return neighborBitFields;
    }

    public boolean isAllPeersFinished() {
        return allPeersFinished;
    }
}
