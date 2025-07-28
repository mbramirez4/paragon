package paragon.Service;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import paragon.Model.Player.Player;
import paragon.Service.FilesManager;

public class Authentication {
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
        Player player;
        while (true) {
            String[] playerData = requestUserNamePassword();

            if (!FilesManager.playerFileExists(playerData[0])) {
                System.out.println("Invalid username or password. Please try again.");
                continue;
            }

            Player savedPlayer = FilesManager.loadPlayerFromJson(playerData[0]);
            player = createPlayer(playerData[0], playerData[1]);
            
            if (savedPlayer.equals(player)) {
                return savedPlayer;
            }

            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public static Player registerPlayer() {
        String[] playerData = requestUserNamePassword();
        Player player = createPlayer(playerData[0], playerData[1]);

        FilesManager.savePlayerToJson(player);

        return player;
    }
}
