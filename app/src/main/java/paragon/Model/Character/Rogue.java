package paragon.Model.Character;

import java.util.UUID;

public class Rogue extends Character {
    String abilities;
    String items;

    public Rogue(String name, String abilities, String items) {
        this.name = name;
        this.abilities = abilities;
        this.items = items;

        this.id = UUID.randomUUID();

        this.level = 1;
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

    public int overallPower() {
        return (int) (agility * level * 1.5);
    }
}
