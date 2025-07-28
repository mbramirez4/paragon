package paragon.Model.Character;

public class Factory {
    public static Character createCharacter(
        String type,
        String name
    ) {
        String abilities = "";
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
