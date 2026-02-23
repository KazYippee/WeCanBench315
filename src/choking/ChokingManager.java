package choking;

import peer.PeerManager;

import java.util.Set;

public class ChokingManager {

    private final int localPeerId;
    private final PeerManager peerManager;
    private volatile int optimisticallyUnchokedPeerId;

    public ChokingManager(int localPeerId, PeerManager peerManager) {
        this.localPeerId = localPeerId;
        this.peerManager = peerManager;
        this.optimisticallyUnchokedPeerId = -1;
    }

    public void startPreferredNeighborTimer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void startOptimisticUnchokingTimer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Set<Integer> selectPreferredNeighbors() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int selectOptimisticallyUnchokedNeighbor() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void applyPreferredNeighbors(Set<Integer> preferredIds) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getOptimisticallyUnchokedPeerId() {
        return optimisticallyUnchokedPeerId;
    }
}
