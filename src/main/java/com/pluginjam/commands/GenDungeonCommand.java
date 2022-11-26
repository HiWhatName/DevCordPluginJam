package com.pluginjam.commands;


import com.pluginjam.dungeon.generator.DungeonPieceGenerator;

import com.pluginjam.dungeon.generator.pieces.Quad;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GenDungeonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player p)) return false;

        Quad quad = new Quad(); //TODO: temp
        DungeonPieceGenerator.generateDungeonPiece(quad, p.getLocation());
        p.sendMessage("Pasted an dungeon piece at your cords.");
        return true;
    }

}

