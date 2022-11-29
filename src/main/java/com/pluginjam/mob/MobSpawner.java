package com.pluginjam.mob;

import com.pluginjam.dungeon.danger.DangerManager;
import com.pluginjam.mob.mobs.*;
import com.pluginjam.util.Util;
import com.pluginjam.util.WeightedRandomBag;
import org.bukkit.Difficulty;

import java.util.Random;

public class MobSpawner {
    //private boolean isSpawning = false; Not needed
    private final DangerManager dangerManager;
    private Util util;
    private Random rnd;
    WeightedRandomBag<DungeonMob> mobProbability;

    public MobSpawner(DangerManager dangerManager) {
        this.dangerManager = dangerManager;
        this.util = new Util();
        this.rnd = new Random();
        this.mobProbability = new WeightedRandomBag<>();

    }

    public void spawnRandomDungeonMob(SpawnMeta sm){
        reevaluateChances(sm.world().getDifficulty());
        mobProbability.getRandom().spawn(sm);
    }
    private void reevaluateChances(Difficulty difficulty){
        int level = util.dangerToMobLevel(this.dangerManager.getDangerLevel(), (1f + (difficulty.getValue() -1f) / 4));
        boolean miniBoss = Util.genMiniBossChance(level, rnd);
        mobProbability = null; // Clear previous array of entries.
        this.mobProbability = new WeightedRandomBag<>(); // Reassign it.

        /* -----------------Mob Chances-------------------|
        Witch: 3%
        Creeper: 4%
        Spider: 6%               -| Spiders combined = 10%
        SpiderJockey: 4%  -|
        Skeleton: 8%
        Zombie: 10%
        Zoglin: 1.5% if danger > 10. Then per level + 1%
        Warden if lvl > 15
        |-----------------------END-----------------------*/

        mobProbability.addEntry(new WitchMob(level, miniBoss), 3.0);
        mobProbability.addEntry(new CreeperMob(level, miniBoss),   4.0);
        mobProbability.addEntry(new SpiderMob(level, miniBoss),  6.0);
        mobProbability.addEntry(new SpiderJockeyMob(level, miniBoss),   4.0);
        mobProbability.addEntry(new SkeletonMob(level, miniBoss),  10.0);
        mobProbability.addEntry(new ZombieMob(level, miniBoss), 10.0);
        if(level > 11){ mobProbability.addEntry(new ZoglinMob(level, miniBoss), 1.0 + ((level - 10) /2));} //After lvl 11 we'll add 0.5% chance each lvl
        if(level > 15){ mobProbability.addEntry(new WardenMob(level), 3 + (level - 12)); }
    }
}
