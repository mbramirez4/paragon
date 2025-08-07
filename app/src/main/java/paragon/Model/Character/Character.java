package paragon.Model.Character;

import java.util.ArrayList;
import java.util.UUID;
import paragon.Model.Abilities.Ability;

public abstract class Character implements Fighter {
    protected static final int BASE_DAMAGE_STRENGTH_ATTACK = 10;
    
    protected UUID id;
    protected String name;
    protected int level;
    protected int healthPoints;
    protected int manaPoints;
    protected int currentHealthPoints;
    protected int currentManaPoints;
    protected boolean alive;
    
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

    @Override
    public int strengthAttack() {
        return BASE_DAMAGE_STRENGTH_ATTACK + this.strength;
    }

    @Override
    public int abilityAttack(Ability ability) throws Exception {
        if (currentManaPoints < ability.getManaCost()) {
            throw new Exception("Not enough mana to use ability");
        }

        currentManaPoints -= ability.getManaCost();
        return ability.getBaseDamage() + this.intelligence;
    }

    @Override
    public void receiveDamage(int damage) {
        currentHealthPoints -= damage;

        if (currentHealthPoints <= 0) {
            currentHealthPoints = 0;
            die();
        }
    }

    @Override
    public String toString() {
        String representation;
        representation= this.getClass().getSimpleName();
        representation += " [name=" + name + ", level=" + level;
        representation += ", HP=" + currentHealthPoints + "/" + healthPoints;
        representation += ", MP=" + currentManaPoints + "/" + manaPoints;
        representation += ", agility=" + agility;
        representation += ", strength=" + strength;
        representation += ", intelligence=" + intelligence + "]";

        return representation;
    }

    protected static int initializeAttribute(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public void levelUp() {
        level++;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    public void restoreHealth() {
        currentHealthPoints = healthPoints;
    }

    public void restoreMana() {
        currentManaPoints = manaPoints;
    }

    
    public boolean isAlive() {
        return alive;
    }
    
    public void die() {
        alive = false;
    }

    public void revive() {
        alive = true;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void restoreAttributes() {
        if (!isAlive()) {
            revive();
        }
        restoreHealth();
        restoreMana();
    }
}
