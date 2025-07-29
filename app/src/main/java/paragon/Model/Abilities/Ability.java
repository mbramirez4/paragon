package paragon.Model.Abilities;

import paragon.Service.JsonStorable;

public class Ability {
    private String name;
    private int manaCost;
    private int baseDamage;

    public Ability(String name, int manaCost, int baseDamage) {
        this.name = name;
        this.manaCost = manaCost;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public String toString() {
        return "Ability [name=" + name + ", manaCost=" + manaCost + ", baseDamage=" + baseDamage + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Ability other = (Ability) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        
        // the name is the same, so we can compare the other fields
        return (
            manaCost == other.manaCost
            && baseDamage == other.baseDamage
        );
    }
}
