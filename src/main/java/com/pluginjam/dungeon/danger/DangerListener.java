package com.pluginjam.dungeon.danger;

import com.pluginjam.dungeon.DungeonOre;
import com.pluginjam.dungeon.generator.world.DungeonWorld;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.text.DecimalFormat;

public class DangerListener implements Listener {

    DecimalFormat dangerFormat = new DecimalFormat("#,##0.0");
    private final DangerManager dangerManager;
    private BossBar dangerBar = Bukkit.createBossBar("Current danger LvL: 0.0", BarColor.GREEN, BarStyle.SOLID);
    public DangerListener(DangerManager dangerManager) {
        this.dangerManager = dangerManager;
    }

    @EventHandler
    public void onBreakOre(BlockBreakEvent e) {
        if(!(e.getBlock().getWorld() == Bukkit.getWorld("dungeon"))) return; // Todo: Remove hardcoded 'dungeon' var.
        Player p = e.getPlayer();

        DungeonOre dungeonOre = DungeonOre.getFromMaterial(e.getBlock().getType());
        if (dungeonOre != null && p.getGameMode() != GameMode.CREATIVE) {
            e.getBlock().setType(Material.STONE);
            dangerManager.increaseDangerLevel(dungeonOre.getDangerIncrease());
        }
        if(!(p.hasPermission("dungeon.mine") || p.getGameMode() == GameMode.CREATIVE)){
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Can't break this!"));
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 1f);
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(!(e.getBlock().getWorld() == Bukkit.getWorld("dungeon"))) return; // Todo: Remove hardcoded 'dungeon' var.
        Player p = e.getPlayer();

        if(!(p.hasPermission("dungeon.build") || p.getGameMode() == GameMode.CREATIVE)){
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Can't build here!"));
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 1f);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDangerChange(DangerChangeEvent e){
        for(Player p : Bukkit.getOnlinePlayers()){
            BarColor dangerColor;
            if(e.getDanger() <= 5){
                dangerColor = BarColor.GREEN;
            }else if(e.getDanger() <= 10){
               dangerColor = BarColor.YELLOW;
            }else{
                dangerColor = BarColor.RED;
            }

            dangerBar.setTitle("Current danger LvL: " + dangerFormat.format(e.getDanger())); // Round to 1 decimal
            dangerBar.setColor(dangerColor);
            dangerBar.setVisible(true);
            dangerBar.addPlayer(p);
    }
    }
    public BossBar getDangerBar(){
        return dangerBar;
    }


}
