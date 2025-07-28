package paragon.Model.Character;

import java.util.UUID;

public abstract class Character implements Fighter {
    protected UUID id;
    protected String name;
    protected int level;
    protected int healthPoints;
    protected int manaPoints;
    protected int agility;
    protected int strength;
    protected int intelligence;

    public void receiveDamage(int damage) {
        healthPoints -= damage;
    }

    protected static int initializeAttribute(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
