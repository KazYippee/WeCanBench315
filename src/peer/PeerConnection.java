package peer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class PeerConnection {

    private final int remotePeerId;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private volatile boolean choked;
    private volatile boolean interested;
    private volatile boolean remoteChoked;
    private volatile boolean remoteInterested;
    private final AtomicLong bytesDownloadedFromPeer;

    public PeerConnection(int remotePeerId, Socket socket) throws IOException {
        this.remotePeerId = remotePeerId;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.choked = true;
        this.interested = false;
        this.remoteChoked = true;
        this.remoteInterested = false;
        this.bytesDownloadedFromPeer = new AtomicLong(0);
    }

    public int getRemotePeerId() {
        return remotePeerId;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getInputStream() {
        return in;
    }

    public DataOutputStream getOutputStream() {
        return out;
    }

    public boolean isChoked() {
        return choked;
    }

    public boolean isInterested() {
        return interested;
    }

    public boolean isRemoteChoked() {
        return remoteChoked;
    }

    public boolean isRemoteInterested() {
        return remoteInterested;
    }

    public void setChoked(boolean choked) {
        this.choked = choked;
    }

    public void setInterested(boolean interested) {
        this.interested = interested;
    }

    public void setRemoteChoked(boolean remoteChoked) {
        this.remoteChoked = remoteChoked;
    }

    public void setRemoteInterested(boolean remoteInterested) {
        this.remoteInterested = remoteInterested;
    }

    public void addBytesDownloaded(long bytes) {
        bytesDownloadedFromPeer.addAndGet(bytes);
    }

    public long getBytesDownloaded() {
        return bytesDownloadedFromPeer.get();
    }

    public long resetBytesDownloaded() {
        return bytesDownloadedFromPeer.getAndSet(0);
    }

    public void close() throws IOException {
        socket.close();
    }
}
