package paragon.Model.Player;

import java.util.ArrayList;
import paragon.Model.Character.Character;
import paragon.Model.Character.Factory;
import paragon.Model.Abilities.Ability;
import paragon.Service.JsonStorable;
import paragon.Service.FilesManager;

public class Player implements JsonStorable {
    private String userName;
    private String password;
    private Character currentCharacter;
    private ArrayList<Character> characters;

    private static final String ROOT_DIRECTORY = "players";

    public Player() {
        this("user", "user");
    }

    public Player(String userName, String password) {
        this(userName, password, new ArrayList<>());
    }

    public Player(String userName, String password, ArrayList<Character> characters) {
        this.userName = userName;
        this.password = password;
        this.characters = characters;
    }

    @Override
    public String getRootDirectory() {
        return ROOT_DIRECTORY;
    }

    @Override
    public String getFilePath() {
        return ROOT_DIRECTORY + "/" + userName + ".json";
    }

    @Override
    public void save() {
        FilesManager.saveToJson(this);
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public String listCharacters() {
        System.out.println("Number of characters: " + characters.size());
        String charactersList = "";
        for (Character character : characters) {
            charactersList += character.toString() + "\n";
        }
        return charactersList;
    }

    @Override
    public String toString() {
        return "Player [userName=" + userName + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Player) {
            return (
                this.userName.equals(((Player) obj).userName)
                && this.password.equals(((Player) obj).password)
            );
        }
        
        return false;
    }

    public void createCharacter(
        String type, String name, ArrayList<Ability> abilities
    ) {
        addCharacter(Factory.createCharacter(type, name, abilities));
        save();
    }

    private void addCharacter(Character character) {
        characters.add(character);
    }
}
