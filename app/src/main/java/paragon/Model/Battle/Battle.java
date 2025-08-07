package paragon.Model.Battle;

import java.util.ArrayList;
import java.util.Scanner;
import paragon.Model.Player.Player;
import paragon.Model.Character.Character;
import paragon.Model.Abilities.Ability;

public class Battle {
    private static Scanner scanner = new Scanner(System.in);

    private Player p1;
    private Player p2;

    public Battle(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getPlayerAction(Player player) {
        String optionsMessage = player.getUserName() + ", what would you like to do?\n";
        optionsMessage += "1. Attack\n";
        optionsMessage += "2. Use ability\n";
        // optionsMessage += "3. Use item\n";

        int option;
        while (true) {
            System.out.println(optionsMessage);
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > 2) {
                System.out.println("Invalid option. Please select a valid option.");
                continue;
            }

            break;
        }

        switch (option) {
            case 1:
                return "attack";
            case 2:
                return "ability";
            // case 3:
            //     return "item";
            default:
                return null;
        }
    }

    public Ability chooseAbility(Character character) {
        String optionsMessage = character.toString() + "\n";
        optionsMessage += "Which ability would you like to use?\n";
        
        ArrayList<Ability> abilities = character.getAbilities();
        for (int i = 0; i < abilities.size(); i++) {
            optionsMessage += (i + 1) + ". " + abilities.get(i).toString() + "\n";
        }

        int option;
        while (true) {
            System.out.println(optionsMessage);
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > abilities.size()) {
                System.out.println("Invalid option. Please select a valid option.");
                continue;
            }

            break;
        }

        return abilities.get(option - 1);
    }

    public void performPlayerTurn(Player currentPlayer, Player opponent) {
        Character currentCharacter = currentPlayer.getCurrentCharacter();
        Character opponentCharacter = opponent.getCurrentCharacter();
        
        System.out.println("\n=== " + currentPlayer.getUserName() + "'s Turn ===");
        System.out.println("Current character: " + currentCharacter.toString());
        System.out.println("Opponent: " + opponentCharacter.toString());
        
        String action = getPlayerAction(currentPlayer);
        
        switch (action) {
            case "attack":
                performAttack(currentCharacter, opponentCharacter, currentPlayer.getUserName());
                break;
            case "ability":
                performAbilityAttack(currentCharacter, opponentCharacter, currentPlayer.getUserName());
                break;
            // case "item":
            //     System.out.println("Item usage not implemented yet.");
            //     break;
        }
    }

    private void performAttack(Character attacker, Character defender, String attackerName) {
        int damage = attacker.strengthAttack();
        System.out.println(attackerName + " performs a strength attack!");
        System.out.println("Damage dealt: " + damage);
        
        defender.receiveDamage(damage);
        System.out.println(defender.getName() + " receives " + damage + " damage!");
        System.out.println(defender.getName() + " HP: " + defender.getCurrentHealthPoints() + "/" + defender.getHealthPoints());
        
        if (!defender.isAlive()) {
            System.out.println(defender.getName() + " has been defeated!");
        }
    }

    private void performAbilityAttack(Character attacker, Character defender, String attackerName) {
        try {
            Ability chosenAbility = chooseAbility(attacker);
            System.out.println(attackerName + " uses ability: " + chosenAbility.getName());
            
            int damage = attacker.abilityAttack(chosenAbility);
            System.out.println("Damage dealt: " + damage);
            
            defender.receiveDamage(damage);
            System.out.println(defender.getName() + " receives " + damage + " damage!");
            System.out.println(defender.getName() + " HP: " + defender.getCurrentHealthPoints() + "/" + defender.getHealthPoints());
            
            if (!defender.isAlive()) {
                System.out.println(defender.getName() + " has been defeated!");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Turn wasted due to insufficient mana!");
        }
    }

    public Player checkWinner() {
        Character p1Character = p1.getCurrentCharacter();
        Character p2Character = p2.getCurrentCharacter();
        
        if (!p1Character.isAlive() && p2Character.isAlive()) {
            return p2;
        } else if (p1Character.isAlive() && !p2Character.isAlive()) {
            return p1;
        }
        
        return null; // No winner yet or both dead
    }

    public Player play() {
        System.out.println("=== BATTLE START ===");
        System.out.println(p1.getUserName() + " vs " + p2.getUserName());
        
        Character p1Character = p1.getCurrentCharacter();
        Character p2Character = p2.getCurrentCharacter();
        
        p1Character.restoreAttributes();
        p2Character.restoreAttributes();
        
        System.out.println("Initial status:");
        System.out.println(p1Character.toString());
        System.out.println(p2Character.toString());
        
        int turn = 1;
        
        while (true) {
            System.out.println("\n=== TURN " + turn + " ===");
            
            // Player 1's turn
            if (p1Character.isAlive()) {
                performPlayerTurn(p1, p2);
                
                // Check if p2 is defeated
                Player winner = checkWinner();
                if (winner != null) {
                    System.out.println("\n=== BATTLE END ===");
                    System.out.println(winner.getUserName() + " wins the battle!");

                    p1Character.restoreAttributes();
                    p2Character.restoreAttributes();
                    return winner;
                }
            }
            
            // Player 2's turn
            if (p2Character.isAlive()) {
                performPlayerTurn(p2, p1);
                
                // Check if p1 is defeated
                Player winner = checkWinner();
                if (winner != null) {
                    System.out.println("\n=== BATTLE END ===");
                    System.out.println(winner.getUserName() + " wins the battle!");

                    p1Character.restoreAttributes();
                    p2Character.restoreAttributes();
                    return winner;
                }
            }
            
            // If both players are dead (shouldn't happen in normal gameplay)
            if (!p1Character.isAlive() && !p2Character.isAlive()) {
                System.out.println("\n=== BATTLE END ===");
                System.out.println("It's a draw! Both players are defeated!");

                p1Character.restoreAttributes();
                p2Character.restoreAttributes();
                return null;
            }
            
            turn++;
        }
    }
}
