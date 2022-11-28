package com.pluginjam.dungeon.generator.world;


import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.generator.ChunkGenerator;

public class DungeonWorld extends ChunkGenerator {
    World Dungeon;
    Location SpawnPoint = new Location(Dungeon, 0,75,0);
    public DungeonWorld(String WorldName) {
        WorldCreator wc = new WorldCreator(WorldName);
        wc.generator(new DungeonChunkGenerator()); //The chunk generator from step 1
        Dungeon = wc.createWorld();

        //Dungeon.setStorm(false);
        //Dungeon.setDifficulty(Difficulty.NORMAL);

        //TODO: FIX
    }



}
