package com.narxoz.rpg.enemy.basic;

import com.narxoz.rpg.enemy.Enemy;

public class BasicEnemy implements Enemy {
    private String title;
    private int damage;
    private int health;
    private int maxHealth;

    public BasicEnemy(String title, int damage, int health) {
        this.title = title;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void applyDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public boolean isDefeated() {
        return health <= 0;
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
