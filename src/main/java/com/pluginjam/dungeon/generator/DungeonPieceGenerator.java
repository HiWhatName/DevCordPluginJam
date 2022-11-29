package com.pluginjam.dungeon.generator;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.mask.BlockMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.BlockVector3Imp;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Location;

import java.util.Random;

public class DungeonPieceGenerator {

    public static void generateDungeonPiece(DungeonPieceSchematic piece, Location location) {

        Clipboard clipboard = piece.loadSchemFile();
        WorldEdit worldEdit = WorldEdit.getInstance();
        World bukkitWorld = BukkitAdapter.adapt(location.getWorld());

        //Paste
        try (EditSession editSession = worldEdit.newEditSession(bukkitWorld)) { //sus
             Mask mask = new BlockMask(editSession.getExtent(), new BaseBlock(BlockTypes.get(
                    "minecraft:red_concrete")));
            editSession.replaceBlocks(clipboard.getRegion(), mask, new BaseBlock(BlockType.REGISTRY.get("minecraft:air")));
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                    // configure here
                    .build();
            Operations.complete(operation);
        }

        try (EditSession editSession = worldEdit.newEditSession(bukkitWorld)) {

        }
    }

    public static DungeonPieceSchematic getRandomVariant(DungeonPiece dungeonPiece) {
        String label;
        if (dungeonPiece instanceof SingleVariantDungeonPiece singleVariantDungeonPiece) {
            label = singleVariantDungeonPiece.getLabel();
        } else {
            String[] variants = dungeonPiece.getVariants();
            label = variants[new Random().nextInt(variants.length)];
        }
        return new DungeonPieceSchematic(label);
    }
}
