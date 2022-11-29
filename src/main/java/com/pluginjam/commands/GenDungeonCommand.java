package com.pluginjam.commands;


import com.pluginjam.dungeon.generator.DungeonPieceGenerator;
import com.pluginjam.dungeon.generator.DungeonPieceSchematic;
import com.pluginjam.dungeon.generator.pieces.SpawnPiece;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GenDungeonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (player.hasPermission("dungeon.gencommand")) {
            SpawnPiece spawnPiece = new SpawnPiece(); //TODO: temp
            DungeonPieceSchematic randomVariant = DungeonPieceGenerator.getRandomVariant(spawnPiece);
            Location location = player.getLocation();
             DungeonPieceGenerator.generateDungeonPiece(randomVariant, location);

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            player.sendMessage(ChatColor.YELLOW + "Pasted" + spawnPiece.getLabel() + " at X:" + Math.round(player.getLocation().getX()) + " Y:" + Math.round(player.getLocation().getY()) + "Z:" + Math.round(player.getLocation().getZ()));
            return true;
        } else {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            player.sendMessage(ChatColor.RED + "No permission!");
            return true;
        }
    }

}

