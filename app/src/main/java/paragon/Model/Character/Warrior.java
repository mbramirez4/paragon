package paragon.Model.Character;

import java.util.ArrayList;
import paragon.Model.Abilities.Ability;

public class Warrior extends Character {
    String items;

    public Warrior(String name, ArrayList<Ability> abilities, String items) {
        super(name, abilities, items);

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
