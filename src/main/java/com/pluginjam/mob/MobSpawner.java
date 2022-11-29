package com.pluginjam.mob;

import com.pluginjam.dungeon.danger.DangerManager;
import com.pluginjam.util.Util;
import org.bukkit.Difficulty;
import org.bukkit.Location;

import java.util.Random;

public class MobSpawner {
    private boolean isSpawning = false;
    private final DangerManager dangerManager;
    private Util util;
    private Random rnd;

    public MobSpawner(DangerManager dangerManager) {
        this.dangerManager = dangerManager;
        this.util = new Util();
        this.rnd = new Random();
    }
    public void spawnMob(DungeonMob<?> mob, SpawnMeta spawnMeta, int level) {
        boolean miniBoss = util.genMiniBossChance(level, rnd);
        mob.spawn(spawnMeta);
    }

    public void spawnRandomDungeonMob(float danger, SpawnMeta spawnMeta){
        int level = util.dangerToMobLevel(danger, (1f + (spawnMeta.world().getDifficulty().getValue() -1f) / 4)); // Deprecated for years, but returns 1/2/3 on easy/normal/hard e.g: A(Hard) = 50% higher lvl.


        /*
        Witch: 3%
        Creeper: 4%
        Spider: 6%               -| Spider's = 10%
        SpiderJockey: 4%  -|
        Skeleton: 8%
        Zombie: 10%
        Zoglin: 2% if danger > 10. Then per danger level + 1%
        --------------------------
        Warden if lvl > 15
         */
    }

    /*===These functions aren't needed since we'll spawn them when pasting in the shem file===*
    public void startSpawning() {
        if (!isSpawning) {
            float dangerLevel = dangerManager.getDangerLevel();
            isSpawning = true;
        }
    }

    public void stopSpawning() {
        if (isSpawning) {
            isSpawning = false;
        }
    }
       */
}
