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
        try {
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed() && !peerManager.isAllPeersFinished()) {
                try {
                    Socket socket = serverSocket.accept();
                    Thread handler = new Thread(new ConnectionHandler(socket, localPeerId, peerManager, false));
                    handler.setDaemon(true);
                    handler.start();
                } catch (IOException e) {
                    if (!serverSocket.isClosed()) {
                        System.err.println("Accept error: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot bind server socket on port " + port + ": " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
