package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeerInfoConfig {

    private static PeerInfoConfig instance;
    private final List<PeerInfo> peers = new ArrayList<>();

    private PeerInfoConfig() {}

    public static PeerInfoConfig getInstance() {
        if (instance == null) {
            instance = new PeerInfoConfig();
        }
        return instance;
    }

    public void load(String filePath) throws IOException {
        peers.clear();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                String[] parts = line.split("\\s+");
                int peerId = Integer.parseInt(parts[0]);
                String hostName = parts[1];
                int port = Integer.parseInt(parts[2]);
                boolean hasFile = parts[3].equals("1");
                peers.add(new PeerInfo(peerId, hostName, port, hasFile));
            }
        }
        reader.close();
    }

    public List<PeerInfo> getPeers() {
        return Collections.unmodifiableList(peers);
    }

    public PeerInfo getPeerById(int peerId) {
        for (PeerInfo peer : peers) {
            if (peer.getPeerId() == peerId) {
                return peer;
            }
        }
        return null;
    }

    public List<PeerInfo> getPeersBefore(int peerId) {
        List<PeerInfo> result = new ArrayList<>();
        for (PeerInfo peer : peers) {
            if (peer.getPeerId() == peerId) break;
            result.add(peer);
        }
        return result;
    }
}
