package com.pluginjam.mob;

import com.pluginjam.dungeon.danger.DangerManager;
import org.bukkit.Location;

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

}
