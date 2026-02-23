package peer;

import config.CommonConfig;
import config.PeerInfo;
import config.PeerInfoConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PeerManager {

    private final int localPeerId;
    private final BitField localBitField;
    private final Map<Integer, PeerConnection> connections;
    private final Map<Integer, BitField> neighborBitFields;
    private volatile boolean allPeersFinished;

    public PeerManager(int localPeerId, boolean hasFile) {
        this.localPeerId = localPeerId;
        int numPieces = CommonConfig.getInstance().getNumberOfPieces();
        this.localBitField = new BitField(numPieces, hasFile);
        this.connections = new ConcurrentHashMap<>();
        this.neighborBitFields = new ConcurrentHashMap<>();
        this.allPeersFinished = false;
    }

    public void start() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void addConnection(int remotePeerId, PeerConnection connection) {
        connections.put(remotePeerId, connection);
        neighborBitFields.put(remotePeerId, new BitField(CommonConfig.getInstance().getNumberOfPieces()));
    }

    public void updateNeighborBitField(int remotePeerId, BitField bitField) {
        neighborBitFields.put(remotePeerId, bitField);
    }

    public void setNeighborHasPiece(int remotePeerId, int pieceIndex) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean checkAllFinished() {
        throw new UnsupportedOperationException("Not yet implemented");
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
