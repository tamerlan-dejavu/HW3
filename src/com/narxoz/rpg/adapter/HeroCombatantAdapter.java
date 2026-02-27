package com.narxoz.rpg.adapter;

import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.hero.Hero;

public class HeroCombatantAdapter implements Combatant {
    private final Hero hero;

    public HeroCombatantAdapter(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String getName() {
        return hero.getName();
    }

    @Override
    public int getAttackPower() {
        return hero.getPower();
    }

    @Override
    public void takeDamage(int amount) {
        hero.receiveDamage(amount);
    }

    @Override
    public boolean isAlive() {
        return hero.isAlive();
    }

    @Override
    public int getCurrentHealth() {
        return hero.getHealth();
    }
    
    @Override
    public int getMaxHealth() {
        return hero.getMaxHealth();
    }
    
    public Hero getHero() {
        return hero;
    }
}
