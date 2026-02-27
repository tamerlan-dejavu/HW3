package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.basic.Creep;
import com.narxoz.rpg.enemy.basic.Goblin;
import com.narxoz.rpg.enemy.boss.Demon;
import com.narxoz.rpg.enemy.boss.Dragon;
import com.narxoz.rpg.hero.basic.Mage;
import com.narxoz.rpg.hero.basic.Warrior;
import com.narxoz.rpg.hero.boss.Archmage;
import com.narxoz.rpg.hero.boss.Lord;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Battle Engine Demo ===\n");

        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        Archmage archmage = new Archmage("Harry Potter");
        Lord lord = new Lord("Arthur");

        Goblin goblin = new Goblin("Dart Goblin");
        Creep creep = new Creep("Weirdo");
        Dragon dragon = new Dragon("Ancient Dragon");
        Demon demon = new Demon("Mefistofel");




        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));
        heroes.add(new HeroCombatantAdapter(archmage));
        heroes.add(new HeroCombatantAdapter(lord));

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(goblin));
        enemies.add(new EnemyCombatantAdapter(creep));
        enemies.add(new EnemyCombatantAdapter(dragon));
        enemies.add(new EnemyCombatantAdapter(demon));



        System.out.println("INITIAL TEAMS:");
        System.out.println("  Heroes:");
        for (Combatant hero : heroes) {
            System.out.println("    - " + hero.getName() + " [HP: " + hero.getCurrentHealth() + "/" + hero.getMaxHealth() + "]");
        }
        System.out.println("  Enemies:");
        for (Combatant enemy : enemies) {
            System.out.println("    - " + enemy.getName() + " [HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + "]");
        }
        System.out.println();


        BattleEngine engineA = BattleEngine.getInstance();
        BattleEngine engineB = BattleEngine.getInstance();
        System.out.println("Same instance? " + (engineA == engineB));
        System.out.println();


        engineA.setRandomSeed(42L);
        EncounterResult result = engineA.runEncounter(heroes, enemies);

        for (String line : result.getBattleLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== BATTLE STATISTICS ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Total Rounds: " + result.getRounds());

        System.out.println("\nSurvivors:");
        if (result.getWinner().equals("Team A")) {
            for (Combatant hero : heroes) {
                if (hero.isAlive()) {
                    System.out.println("  - " + hero.getName() + " [HP: " + hero.getCurrentHealth() + "/" + hero.getMaxHealth() + "]");
                }
            }
        } else {
            for (Combatant enemy : enemies) {
                if (enemy.isAlive()) {
                    System.out.println("  - " + enemy.getName() + " [HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + "]");
                }
            }
        }
        System.out.println();
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());

        System.out.println("\n=== Demo Complete ===");
    }
}
