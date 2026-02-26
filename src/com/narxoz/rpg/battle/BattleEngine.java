package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        this.random = new Random(1L);
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        if (teamA == null || teamB == null || teamA.isEmpty() || teamB.isEmpty()) {
            throw new IllegalArgumentException("Teams cannot be null or empty");
        }

        EncounterResult result = new EncounterResult();
        List<Combatant> aliveTeamA = new ArrayList<>(teamA);
        List<Combatant> aliveTeamB = new ArrayList<>(teamB);
        
        result.addLog("=== Battle Start ===");
        result.addLog("Team A: " + getTeamNames(aliveTeamA));
        result.addLog("Team B: " + getTeamNames(aliveTeamB));
        result.addLog("");

        int round = 0;
        while (!aliveTeamA.isEmpty() && !aliveTeamB.isEmpty()) {
            round++;
            result.addLog("--- Round " + round + " ---");

            for (Combatant attacker : aliveTeamA) {
                if (aliveTeamB.isEmpty()) break;
                Combatant target = aliveTeamB.get(random.nextInt(aliveTeamB.size()));
                int damage = attacker.getAttackPower();
                
                target.takeDamage(damage);
                result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage");
                
                if (!target.isAlive()) {
                    result.addLog(target.getName() + " has been defeated!");
                    aliveTeamB.remove(target);
                }
            }

            if (aliveTeamB.isEmpty()) break;
            for (Combatant attacker : aliveTeamB) {
                if (aliveTeamA.isEmpty()) break;

                Combatant target = aliveTeamA.get(random.nextInt(aliveTeamA.size()));
                int damage = attacker.getAttackPower();
                
                target.takeDamage(damage);
                result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage");
                
                if (!target.isAlive()) {
                    result.addLog(target.getName() + " has been defeated!");
                    aliveTeamA.remove(target);
                }
            }
            
            result.addLog("");
        }

        result.setRounds(round);
        
        if (!aliveTeamA.isEmpty()) {
            result.setWinner("Team A");
            result.addLog("=== Team A Wins! ===");
        } else {
            result.setWinner("Team B");
            result.addLog("=== Team B Wins! ===");
        }

        return result;
    }

     private String getTeamNames(List<Combatant> team) {
    if (team.isEmpty()) return "";
    
    String result = team.get(0).getName();
    for (int i = 1; i < team.size(); i++) {
        result = result + ", " + team.get(i).getName();
    }
    return result;
}
}
