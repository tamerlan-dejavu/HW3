package com.narxoz.rpg.hero.basic;

import com.narxoz.rpg.hero.Hero;

public class BasicHero implements Hero {
    private String name;
    private int power;
    private int health;
    private int maxHealth;

    public BasicHero(String name, int power, int health) {
        this.name = name;
        this.power = power;
        this.health = health;
        this.maxHealth = health;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void receiveDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
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
}
