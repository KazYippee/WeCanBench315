package network;

import peer.PeerManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable {

    private final int port;
    private final int localPeerId;
    private final PeerManager peerManager;
    private ServerSocket serverSocket;

    public ConnectionListener(int port, int localPeerId, PeerManager peerManager) {
        this.port = port;
        this.localPeerId = localPeerId;
        this.peerManager = peerManager;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void stop() throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
