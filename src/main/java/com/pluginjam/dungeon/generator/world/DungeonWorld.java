package com.pluginjam.dungeon.generator.world;


import org.bukkit.*;
public class DungeonWorld{
    World Dungeon;

    Location SpawnPoint;
    public DungeonWorld(String WorldName) {
        WorldCreator wc = new WorldCreator(WorldName);
        wc.generator(new DungeonChunkGenerator());
        Dungeon = wc.createWorld();

        this.SpawnPoint = new Location(Dungeon, 0,75,0); // Relative to Spawn-room

        Dungeon.setDifficulty(Difficulty.NORMAL);
        Dungeon.setStorm(false);
        Dungeon.setPVP(false);

        Dungeon.setGameRule(GameRule.DO_FIRE_TICK, false);
        Dungeon.setGameRule(GameRule.DO_TRADER_SPAWNING, false); // Well... I don't really want him here, do I?
        Dungeon.setGameRule(GameRule.DO_PATROL_SPAWNING, false); // TODO: Maybe implement this as a (5 * dangerLevel)% chance on a new Piece spawn. // (Raid or something)
        Dungeon.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        Dungeon.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Dungeon.setGameRule(GameRule.DO_WEATHER_CYCLE, false);

        Dungeon.setGameRule(GameRule.KEEP_INVENTORY, true); //IDK if we'll keep this tho.

    }

    public void init(){


    }
    public World getDungeonWorld(){
        return Dungeon;
    }
    public Location getSpawnPoint(){
        return this.SpawnPoint;
    }



}
