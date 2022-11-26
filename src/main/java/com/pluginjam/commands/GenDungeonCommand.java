package com.pluginjam.commands;

import com.pluginjam.PluginJam;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GenDungeonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //if(!(sender instanceof Player p)) return false;
        Player p = (Player) sender;
        System.out.println(PluginJam.getInstance().getDataFolder() + "/schem/long_1.schem");

        File schem = new File(PluginJam.getInstance().getDataFolder() + "/schem/long_1.schem");
        Clipboard clipboard = null;

        //Load schem
        ClipboardFormat format = ClipboardFormats.findByFile(schem);
        try (ClipboardReader reader = format.getReader(new FileInputStream(schem))) {
            clipboard = reader.read();
            System.out.println(clipboard);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Paste
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(p.getLocation().getWorld()))){ //sus
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()))
                    // configure here
                    .build();
            Operations.complete(operation);
        }

        return true;
    }
}
