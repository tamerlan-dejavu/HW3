package com.narxoz.rpg.enemy.boss;

import com.narxoz.rpg.enemy.Enemy;

public class BossEnemy implements Enemy {
    private String title;
    private int damage;
    private int health;
    private boolean isEnraged;
    private int maxHealth;
    private int rageThreshold;

    public BossEnemy(String title, int damage, int health, int rageThreshold) {
        this.title = title;
        this.damage = damage;
        this.health = health;
        this.isEnraged = false;
        this.maxHealth = health;
        this.rageThreshold = rageThreshold;
    }

    @Override
    public String getTitle() {
        if (isEnraged) {
            return title + " Enraged";
        }
        return title;
    }

    @Override
    public int getDamage() {
        return isEnraged ? damage * 2 : damage;
    }

    @Override
    public void applyDamage(int amount) {
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
    public boolean isDefeated() {
        return health <= 0;
    }

    public boolean isEnraged(){
        return isEnraged;
    }

    public int getRageMode() {
        return isEnraged ? 2 : 1;
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