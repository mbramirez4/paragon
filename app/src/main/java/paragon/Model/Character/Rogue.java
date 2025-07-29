package paragon.Model.Character;

import java.util.ArrayList;
import paragon.Model.Abilities.Ability;

public class Rogue extends Character {
    String items;

    public Rogue(String name, ArrayList<Ability> abilities, String items) {
        super(name, abilities, items);
        
        this.healthPoints = initializeAttribute(80, 130);
        this.manaPoints = initializeAttribute(40, 70);
        this.agility = initializeAttribute(25, 40);
        this.strength = initializeAttribute(5, 15);
        this.intelligence = initializeAttribute(5, 20);
    }

    @Override
    public String toString() {
        return "Rogue [name=" + name + ", level=" + level + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Rogue) {
            return this.id.equals(((Rogue) obj).id);
        }
        
        return false;
    }


    public void attack() {
        
    }

    public void useAbility() {
        
    }

    public int overallPower() {
        return (int) (agility * level * 1.5);
    }
}
