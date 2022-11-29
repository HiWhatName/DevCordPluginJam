package com.pluginjam.dungeon.generator.world;


import com.pluginjam.dungeon.danger.DangerListener;
import com.pluginjam.dungeon.danger.DangerManager;
import org.bukkit.*;
import org.bukkit.plugin.Plugin;

public class DungeonWorld{
    private World dungeon;
    private Plugin plugin;
    private DangerListener dangerListener;
    private DangerManager dangerManager;
    Location SpawnPoint;
    public DungeonWorld(String WorldName, Plugin plugin) {
        WorldCreator wc = new WorldCreator(WorldName);
        wc.generator(new DungeonChunkGenerator());
        dungeon = wc.createWorld();
        this.plugin = plugin;
        this.SpawnPoint = new Location(dungeon, 0,75,0); // Relative to Spawn-room

        dungeon.setDifficulty(Difficulty.NORMAL);
        dungeon.setStorm(false);
        dungeon.setPVP(false);

        dungeon.setGameRule(GameRule.DO_FIRE_TICK, false);
        dungeon.setGameRule(GameRule.DO_TRADER_SPAWNING, false); // Well... I don't really want him here, do I?
        dungeon.setGameRule(GameRule.DO_PATROL_SPAWNING, false); // TODO: Maybe implement this as a (5 * dangerLevel)% chance on a new Piece spawn. // (Raid or something)
        dungeon.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        dungeon.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        dungeon.setGameRule(GameRule.DO_WEATHER_CYCLE, false);

        dungeon.setGameRule(GameRule.KEEP_INVENTORY, true); //IDK if we'll keep this tho.
        init();

    }

    public void init(){
        this.dangerManager = new DangerManager(dungeon.getDifficulty());
        this.dangerListener = new DangerListener(dangerManager);

        //Register world specific events
        this.plugin.getServer().getPluginManager().registerEvents(new DangerListener(dangerManager), plugin);
    }
    public DangerManager getDangerManager(){
        return this.dangerManager;
    }

    public DangerListener getDangerListener() {
        return dangerListener;
    }

    public World getDungeonWorld(){
        return dungeon;
    }
    public Location getSpawnPoint(){
        return this.SpawnPoint;
    }



}
