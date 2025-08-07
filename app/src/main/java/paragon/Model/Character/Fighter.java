package paragon.Model.Character;

import paragon.Model.Abilities.Ability;

public interface Fighter {
    int strengthAttack();
    int abilityAttack(Ability ability) throws Exception;
    void receiveDamage(int damage);
    int overallPower();
}
