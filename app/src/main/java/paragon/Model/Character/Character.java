package paragon.Model.Character;

import java.util.ArrayList;
import java.util.UUID;
import paragon.Model.Abilities.Ability;

public abstract class Character implements Fighter {
    protected UUID id;
    protected String name;
    protected int level;
    protected int healthPoints;
    protected int manaPoints;
    protected int agility;
    protected int strength;
    protected int intelligence;
    protected ArrayList<Ability> abilities;

    protected Character(String name, ArrayList<Ability> abilities, String items) {
        this.name = name;
        this.abilities = abilities;
        // this.items = items;

        this.id = UUID.randomUUID();

        this.level = 1;
    }

    public void receiveDamage(int damage) {
        healthPoints -= damage;
    }

    protected static int initializeAttribute(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    protected void levelUp() {
        level++;
    }
}
