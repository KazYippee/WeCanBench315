package network;

import peer.PeerConnection;
import peer.PeerManager;

import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private final Socket socket;
    private final int localPeerId;
    private final PeerManager peerManager;
    private final boolean isInitiator;

    public ConnectionHandler(Socket socket, int localPeerId, PeerManager peerManager, boolean isInitiator) {
        this.socket = socket;
        this.localPeerId = localPeerId;
        this.peerManager = peerManager;
        this.isInitiator = isInitiator;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void performHandshake(PeerConnection connection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void exchangeBitfields(PeerConnection connection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleMessages(PeerConnection connection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
