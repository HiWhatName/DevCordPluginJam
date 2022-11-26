package com.pluginjam.commands;


import com.pluginjam.dungeon.generator.DungeonPieceGenerator;
import com.pluginjam.dungeon.generator.DungeonPieceSchematic;
import com.pluginjam.dungeon.generator.pieces.QuadPiece;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GenDungeonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player) || !(player.hasPermission("dungeon.gencommand"))) return false;

        QuadPiece quad = new QuadPiece(); //TODO: temp
        DungeonPieceSchematic randomVariant = DungeonPieceGenerator.getRandomVariant(quad);
        Location location = player.getLocation();
        DungeonPieceGenerator.generateDungeonPiece(randomVariant, location);

        player.sendMessage("Pasted a dungeon piece at your coords.");
        return true;
    }

}

