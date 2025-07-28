package paragon.Service;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import paragon.Model.Player.Player;

public class Authentication {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static String[] requestUserNamePassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide your username: ");
        String userName = scanner.nextLine();
        System.out.println(userName);
        
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println(password);

        // scanner.close();

        return new String[] { userName, password };
    }

    public static Player createPlayer(String userName, String password) {
        Player player = new Player(userName, password);
        return player;
    }

    public static Player loginPlayer() {
        Player player, savedPlayer;
        while (true) {
            String[] playerData = requestUserNamePassword();
            player = createPlayer(playerData[0], playerData[1]);

            if (!FilesManager.fileExists(player.getFilePath())) {
                System.out.println("Invalid username or password. Please try again.");
                continue;
            }

            String playerString = FilesManager.loadFromJson(player);
            savedPlayer = gson.fromJson(playerString, Player.class);
            
            if (savedPlayer.equals(player)) {
                return savedPlayer;
            }

            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public static Player registerPlayer() {
        String[] playerData = requestUserNamePassword();
        Player player = createPlayer(playerData[0], playerData[1]);

        player.save();
        
        return player;
    }
}
