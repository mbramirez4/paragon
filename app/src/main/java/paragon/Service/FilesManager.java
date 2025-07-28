package paragon.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import paragon.Model.Player.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesManager {
    
    private static final String PLAYERS_DIRECTORY = "players";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static boolean savePlayerToJson(Player player) {
        try {
            createPlayersDirectory();
            
            String fileName = player.getUserName() + ".json";
            if (playerFileExists(fileName)) {
                System.out.println("Player data already exists for: " + player.getUserName());
                return false;
            }
            
            Path filePath = Paths.get(PLAYERS_DIRECTORY, fileName);
            
            String jsonContent = gson.toJson(player);
            
            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(jsonContent);
            }
            
            System.out.println("Player data saved successfully to: " + filePath);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
            return false;
        }
    }
    
    private static void createPlayersDirectory() throws IOException {
        Path playersPath = Paths.get(PLAYERS_DIRECTORY);
        if (!Files.exists(playersPath)) {
            Files.createDirectories(playersPath);
        }
    }
    
    public static Player loadPlayerFromJson(String userName) {
        try {
            String fileName = userName + ".json";
            Path filePath = Paths.get(PLAYERS_DIRECTORY, fileName);
            
            if (!Files.exists(filePath)) {
                return null;
            }
            
            String jsonContent = Files.readString(filePath);
            return gson.fromJson(jsonContent, Player.class);
            
        } catch (IOException e) {
            System.err.println("Error loading player data: " + e.getMessage());
            return null;
        }
    }
    
    public static boolean playerFileExists(String userName) {
        String fileName = userName + ".json";
        Path filePath = Paths.get(PLAYERS_DIRECTORY, fileName);
        return Files.exists(filePath);
    }
}
