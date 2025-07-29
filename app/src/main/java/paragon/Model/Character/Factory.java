package paragon.Model.Character;

import java.util.ArrayList;
import paragon.Model.Abilities.Ability;

public class Factory {
    public static Character createCharacter(
        String type,
        String name,
        ArrayList<Ability> abilities
    ) {
        String items = "";
        System.out.println(type);
        switch (type) {
            case "Warrior":
                return new Warrior(name, abilities, items);
            case "Wizard":
                return new Wizard(name, abilities, items);
            case "Rogue":
                return new Rogue(name, abilities, items);
            default:
                throw new IllegalArgumentException("Unknown character type: " + type);
        }
    }
}
