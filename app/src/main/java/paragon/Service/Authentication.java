package paragon.Service;

import java.util.ArrayList;
import java.util.Scanner;

import paragon.Model.Player.Player;

public class Authentication {
    private static ArrayList<Player> players = new ArrayList<>();
    
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
        players.add(player);

        return player;
    }

    public static void listPlayers() {
        for (Player p : players) {
            System.out.println(p);
        }
    }

    public static Player getPlayer(String userName, String password) {
        Player player = new Player(userName, password);
        for (Player p : players) {
            if (p.equals(player)) {
                return p;
            }
        }
        return null;
    }

    public static Player loginPlayer() {
        Player player;
        while (true) {
            String[] playerData = requestUserNamePassword();
            player = getPlayer(playerData[0], playerData[1]);
            
            if (player != null) {
                return player;
            }
            
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public static Player registerPlayer() {
        String[] playerData = requestUserNamePassword();
        return createPlayer(playerData[0], playerData[1]);
    }
}
