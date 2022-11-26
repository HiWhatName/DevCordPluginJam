package com.pluginjam.dungeon.generator.world;

import com.pluginjam.dungeon.generator.DungeonPieceGenerator;
import com.pluginjam.dungeon.generator.DungeonPieceSchematic;
import com.pluginjam.dungeon.generator.pieces.QuadPiece;
import com.pluginjam.dungeon.generator.pieces.SpawnPiece;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class DungeonWorld extends ChunkGenerator{

    World Dungeon;
    Location SpawnPoint = new Location(Dungeon, -2,33,-2);
    public DungeonWorld(String WorldName) {
        WorldCreator wc = new WorldCreator(WorldName);
        wc.generator(new DungeonChunkGenerator()); //The chunk generator from step 1
        wc.createWorld();

        //TODO: FIX
        //Dungeon = Bukkit.getWorld("dungeon");
        //SpawnPiece spawnPiece = new SpawnPiece();
        //DungeonPieceSchematic schematic = DungeonPieceGenerator.getRandomVariant(spawnPiece);
        //DungeonPieceGenerator.generateDungeonPiece(schematic, SpawnPoint);
    }

}
