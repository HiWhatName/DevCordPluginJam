package com.pluginjam.listener;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    World Dungeon = Bukkit.getWorld("dungeon");
    Location SpawnPoint = new Location(Dungeon, 0,30,0);
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!(p.hasPlayedBefore())) {
            p.teleport(SpawnPoint);
            p.setBedSpawnLocation(SpawnPoint, true);
            p.spawnParticle(Particle.CLOUD, p.getLocation(), 320);
            p.playSound(p.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 0.2f, 1.0f);
            p.sendTitle(ChatColor.RED + "The more you have, the worse it is",
                ChatColor.YELLOW + "Plugin by HiWhatName & Earomc", 7, 160, 7);
        }
        //TODO: Change spawn location to start room, start nbs playback.
    }
}
