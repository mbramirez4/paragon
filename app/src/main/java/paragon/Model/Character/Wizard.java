package paragon.Model.Character;

import java.util.ArrayList;
import paragon.Model.Abilities.Ability;

public class Wizard extends Character {
    String items;

    public Wizard(String name, ArrayList<Ability> abilities, String items) {
        super(name, abilities, items);

        this.healthPoints = initializeAttribute(60, 120);
        this.manaPoints = initializeAttribute(50, 100);
        this.agility = initializeAttribute(5, 15);
        this.strength = initializeAttribute(5, 15);
        this.intelligence = initializeAttribute(25, 40);
    }

    @Override
    public String toString() {
        return "Wizard [name=" + name + ", level=" + level + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Wizard) {
            return this.id.equals(((Warrior) obj).id);
        }
        
        return false;
    }

    public void attack() {
        
    }

    public int overallPower() {
        return intelligence * level + manaPoints;
    }
}
