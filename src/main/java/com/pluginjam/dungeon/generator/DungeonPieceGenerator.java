package com.pluginjam.dungeon.generator;

import com.pluginjam.PluginJam;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DungeonPieceGenerator {

    public static void generateDungeonPiece(DungeonPiece piece, Location location) {

        Clipboard clipboard = piece.getClipboard();
        WorldEdit worldEdit = WorldEdit.getInstance();
        World bukkitWorld = BukkitAdapter.adapt(location.getWorld());

        //Paste

        try (EditSession editSession = worldEdit.newEditSession(bukkitWorld)) { //sus
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                    // configure here
                    .build();
            Operations.complete(operation);

        }
        try (EditSession editSession = worldEdit.newEditSession(bukkitWorld)){
            //TODO: Replace sponges
        }


    }
}
