package paragon.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static boolean saveToJson(JsonStorable jsonStorable) {
        try {
            createRootDirectory(jsonStorable);
            
            String filePathString = jsonStorable.getFilePath();
            if (fileExists(filePathString)) {
                return false;
            }
            
            Path filePath = Paths.get(filePathString);
            
            String jsonContent = gson.toJson(jsonStorable);
            
            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(jsonContent);
            }            
            return true;
            
        } catch (IOException e) {
            System.err.println("Error saving object data: " + e.getMessage());
            return false;
        }
    }
    
    private static void createRootDirectory(JsonStorable jsonStorable) throws IOException {
        Path rootPath = Paths.get(jsonStorable.getRootDirectory());
        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }
    }
    
    public static boolean fileExists(String pathString) {
        Path filePath = Paths.get(pathString);
        return Files.exists(filePath);
    }
    
    public static String loadFromJson(JsonStorable jsonStorable) {
        try {
            Path filePath = Paths.get(jsonStorable.getFilePath());
            
            if (!Files.exists(filePath)) {
                return null;
            }
            
            return Files.readString(filePath);
            
        } catch (IOException e) {
            System.err.println("Error loading player data: " + e.getMessage());
            return null;
        }
    }
}
