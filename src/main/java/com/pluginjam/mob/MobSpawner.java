package com.pluginjam.mob;

import com.pluginjam.dungeon.danger.DangerManager;
import com.pluginjam.mob.mobs.SpiderJockeyMob;
import com.pluginjam.mob.mobs.SpiderMob;
import com.pluginjam.mob.mobs.ZombieMob;
import org.bukkit.Location;

import java.util.Random;

public class MobSpawner {

    private boolean isSpawning = false;
    private final DangerManager dangerManager;

    public MobSpawner(DangerManager dangerManager) {
        this.dangerManager = dangerManager;
    }

    public void spawnMob(DungeonMob<?> mob, SpawnMeta spawnMeta) {

    }

    public void startSpawning() {
        if (!isSpawning) {

            float dangerLevel = dangerManager.getDangerLevel();
            //TODO: Implement spawning
            isSpawning = true;
        }
    }

    public void stopSpawning() {
        if (isSpawning) {
            //TODO: Implement stop spawning
            isSpawning = false;
        }
    }

    public void spawnPack(Class<DungeonMob<?>> dungeonMobClass, int packSize, SpawnMeta spawnMeta) {
        Location location = spawnMeta.location();

    }

    //Spawns mobs based on level / difficulty. Higher level -> stronger base bosses
    //TODO: FIX THE 2ND ARGUMENT. PRETTY SIMPLE, EH?
    public static void spawnRandomDungeonMob(int level, Location loc){
        Random rnd = new Random();
        int i = rnd.nextInt(3);
        float f = i + level / 12;
        SpawnMeta sm = new SpawnMeta(loc);

        // ugly code writen under time pressure.
        if(f < 2) {
            ZombieMob zombieMob = new ZombieMob(level);
            zombieMob.spawn(sm);
        }else if(f < 3) {
            SpiderMob spiderMob = new SpiderMob(level);
            spiderMob.spawn(sm);
        }else if(f < 4) {
            //creeperMob.spawn(sm);
        }else if(f < 5) {
            SpiderJockeyMob spiderJockeyMob = new SpiderJockeyMob(level);
            spiderJockeyMob.spawn(sm);
        }else if(f < 6) {
            //hoglinMob.spawn(sm);
        }else if(f < 7) {
            //oglinMob.spawn(sm);
        }else if(f < 8) {
            //wardenMob.spawn(sm)
        }else{
            //wardenMob.spawn(sm);
        }
    }

}
