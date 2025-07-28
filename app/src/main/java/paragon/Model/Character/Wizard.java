package paragon.Model.Character;

public class Wizard extends Character {
    String abilities;
    String items;

    public Wizard(String name, String abilities, String items) {
        this.name = name;
        this.abilities = abilities;
        this.items = items;

        this.id = Character.getNumCharacters();
        Character.incrementNumCharacters();

        this.level = 1;
        this.healthPoints = initializeAttribute(60, 120);
        this.manaPoints = initializeAttribute(50, 100);
        this.agility = initializeAttribute(5, 15);
        this.strength = initializeAttribute(5, 15);
        this.intelligence = initializeAttribute(25, 40);
    }

    public void attack() {
        
    }

    public int overallPower() {
        return intelligence * level + manaPoints;
    }
}
