package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonConfig {

    private static CommonConfig instance;

    private int numberOfPreferredNeighbors;
    private int unchokingInterval;
    private int optimisticUnchokingInterval;
    private String fileName;
    private long fileSize;
    private int pieceSize;
    private int numberOfPieces;

    private CommonConfig() {}

    public static CommonConfig getInstance() {
        if (instance == null) {
            instance = new CommonConfig();
        }
        return instance;
    }

    public void load(String filePath) throws IOException {
        Map<String, String> properties = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                String[] parts = line.split("\\s+", 2);
                if (parts.length == 2) {
                    properties.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
        reader.close();

        numberOfPreferredNeighbors = Integer.parseInt(properties.get("NumberOfPreferredNeighbors"));
        unchokingInterval = Integer.parseInt(properties.get("UnchokingInterval"));
        optimisticUnchokingInterval = Integer.parseInt(properties.get("OptimisticUnchokingInterval"));
        fileName = properties.get("FileName");
        fileSize = Long.parseLong(properties.get("FileSize"));
        pieceSize = Integer.parseInt(properties.get("PieceSize"));
        numberOfPieces = (int) Math.ceil((double) fileSize / pieceSize);
    }

    public int getNumberOfPreferredNeighbors() {
        return numberOfPreferredNeighbors;
    }

    public int getUnchokingInterval() {
        return unchokingInterval;
    }

    public int getOptimisticUnchokingInterval() {
        return optimisticUnchokingInterval;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public int getPieceSize() {
        return pieceSize;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }
}
