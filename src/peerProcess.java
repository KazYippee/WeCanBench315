import config.CommonConfig;
import config.PeerInfo;
import config.PeerInfoConfig;
import logging.PeerLogger;
import peer.PeerManager;

import java.io.IOException;

public class peerProcess {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java peerProcess <peerId>");
            System.exit(1);
        }

        int localPeerId = Integer.parseInt(args[0]);

        try {
            CommonConfig.getInstance().load("Common.cfg");
            PeerInfoConfig.getInstance().load("PeerInfo.cfg");

            PeerInfo localPeer = PeerInfoConfig.getInstance().getPeerById(localPeerId);
            if (localPeer == null) {
                System.err.println("Peer ID " + localPeerId + " not found in PeerInfo.cfg");
                System.exit(1);
            }

            PeerLogger logger = new PeerLogger(localPeerId);
            PeerManager peerManager = new PeerManager(localPeerId, localPeer.hasFile());
            peerManager.start();

        } catch (IOException e) {
            System.err.println("Startup error: " + e.getMessage());
            System.exit(1);
        }
    }
}
