package com.narxoz.rpg.hero.boss;

import com.narxoz.rpg.hero.Hero;

public class BossHero implements Hero{
    private String name;
    private int power;
    private int health;
    private int maxHealth;
    private int rageThreshold;
    private boolean isEnraged;

    public BossHero(String name, int power, int health, int rageThreshold) {
        this.name = name;
        this.power = power;
        this.health = health;
        this.maxHealth = health;
        this.rageThreshold = rageThreshold;
        this.isEnraged = false;
    }

    @Override
    public String getName() {
        if (isEnraged) {
            return name + " Enraged";
        }
        return name;
    }

    @Override
    public int getPower() {
        return isEnraged ? power * 2 : power;
    }

    @Override
    public void receiveDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        checkEnrage();
    }

    public void checkEnrage() {
        if (!isEnraged) {
            double healthPercent = (health * 100.0) / maxHealth;
            if (healthPercent <= rageThreshold) {
                isEnraged = true;
            }
        }
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int getHealth() {
        return health;
    }
    
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getRageMode() {
        return isEnraged ? 2 : 1;
    }
}
