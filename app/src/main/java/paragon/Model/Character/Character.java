package paragon.Model.Character;

public abstract class Character implements Fighter {
    protected int id;
    protected String name;
    protected int level;
    protected int healthPoints;
    protected int manaPoints;
    protected int agility;
    protected int strength;
    protected int intelligence;

    protected static int numCharacters = 0;

    public void receiveDamage(int damage) {
        healthPoints -= damage;
    }

    public static int getNumCharacters() {
        return numCharacters;
    }

    protected static void incrementNumCharacters() {
        numCharacters++;
    }

    protected static int initializeAttribute(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
