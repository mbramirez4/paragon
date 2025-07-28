package paragon.Model.Character;

public class Warrior extends Character {
    String abilities;
    String items;

    public Warrior(String name, String abilities, String items) {
        this.name = name;
        this.abilities = abilities;
        this.items = items;

        this.id = Character.getNumCharacters();
        Character.incrementNumCharacters();

        this.level = 1;
        this.healthPoints = initializeAttribute(100, 170);
        this.manaPoints = initializeAttribute(30, 50);
        this.agility = initializeAttribute(5, 15);
        this.strength = initializeAttribute(25, 40);
        this.intelligence = initializeAttribute(5, 15);;
    }

    public void attack() {
        
    }

    public int overallPower() {
        return strength * level + healthPoints;
    }
}
