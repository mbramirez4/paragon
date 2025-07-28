package paragon.Model.Character;

import java.util.UUID;

public class Warrior extends Character {
    String abilities;
    String items;

    public Warrior(String name, String abilities, String items) {
        this.name = name;
        this.abilities = abilities;
        this.items = items;

        this.id = UUID.randomUUID();

        this.level = 1;
        this.healthPoints = initializeAttribute(100, 170);
        this.manaPoints = initializeAttribute(30, 50);
        this.agility = initializeAttribute(5, 15);
        this.strength = initializeAttribute(25, 40);
        this.intelligence = initializeAttribute(5, 15);;
    }

    @Override
    public String toString() {
        return "Warrior [name=" + name + ", level=" + level + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Warrior) {
            return this.id.equals(((Warrior) obj).id);
        }
        
        return false;
    }

    public void attack() {
        
    }

    public int overallPower() {
        return strength * level + healthPoints;
    }
}
