package com.pluginjam.listener;

import com.pluginjam.dungeon.generator.world.DungeonWorld;
import com.pluginjam.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final DungeonWorld dungeonWorld;

    public JoinListener(DungeonWorld dungeonWorld) {
        this.dungeonWorld = dungeonWorld;
    }
    Location playerSpawnPoint = new Location(Bukkit.getWorld("dungeon"), 0, 75, 0);
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.setBedSpawnLocation(playerSpawnPoint, true);
        player.spawnParticle(Particle.CLOUD, player.getLocation(), 320);
        if(!(player.hasPlayedBefore())) {
            player.teleport(playerSpawnPoint);
            player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 0.2f, 1.0f);
            player.sendTitle(ChatColor.RED + "The more you have, the worse it is",
                ChatColor.YELLOW + "Plugin by HiWhatName & Earomc", 7, 160, 7);
            player.getInventory().setItem(1, new ItemBuilder(Material.STONE_PICKAXE).setName("Broken pickaxe").
                    setLore("Used quite a bit... still seems to work fine tho").addEnchant(Enchantment.DIG_SPEED, (byte) 2).setUnbreakable(true).build());
        }
        //TODO: Change spawn location to start room, start nbs playback.
    }
}
