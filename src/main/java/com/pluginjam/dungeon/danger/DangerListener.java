package com.pluginjam.dungeon.danger;

import com.pluginjam.dungeon.DungeonOre;
import com.pluginjam.mob.DungeonMob;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class DangerListener implements Listener {

    DecimalFormat dangerFormat = new DecimalFormat("#,##0.0");
    private final DangerManager dangerManager;
    private World dungeonWorld;
    public DangerListener(DangerManager dangerManager, World dungeonWorld){
        this.dungeonWorld = dungeonWorld;
        this.dangerManager = dangerManager;
    }
    private BossBar dangerBar = Bukkit.createBossBar("No danger!", BarColor.GREEN, BarStyle.SOLID);
    public DangerListener(DangerManager dangerManager) {
        this.dangerManager = dangerManager;
    }

    @EventHandler
    public void onBreakOre(BlockBreakEvent e) {
       //if(e.getBlock().getWorld().getName().equals(dungeonWorld)) return;
        Player p = e.getPlayer();

        DungeonOre dungeonOre = DungeonOre.getFromMaterial(e.getBlock().getType());
        if (dungeonOre != null && p.getGameMode() != GameMode.CREATIVE) {
            for(ItemStack item : e.getBlock().getDrops(p.getItemInUse(), p)){ // Add items to inv instead of dropping them
                p.getInventory().addItem(item);
            }
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.35f ,1f);
            e.getBlock().setType(Material.STONE);
            e.setDropItems(false);
            dangerManager.increaseDangerLevel(dungeonOre.getDangerIncrease());
        }
        if(dungeonOre == null && !(p.hasPermission("dungeon.mine") || p.getGameMode() == GameMode.CREATIVE)){
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Can't break this!"));
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 1f);
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        //if(!(e.getBlock().getWorld().equals(dungeonWorld))) return;
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
            if(e.getDanger() == 1){
                dangerColor = BarColor.GREEN;
                dangerBar.setTitle("No danger!");
            }
            if(e.getDanger() <= 5){
                dangerColor = BarColor.GREEN;
            }else if(e.getDanger() <= 10){
               dangerColor = BarColor.YELLOW;
            }else{
                dangerColor = BarColor.RED;
            }
            this.dangerBar.setTitle("Current danger LvL: " + dangerFormat.format(e.getDanger())); // Round to 1 decimal
            this.dangerBar.setColor(dangerColor);
            this.dangerBar.setVisible(true);
            this.dangerBar.addPlayer(p);
            }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof DungeonMob<?> mob ){
            this.dangerManager.increaseDangerLevel(1.1f);
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        this.dangerBar.addPlayer(e.getPlayer());
    }
    public BossBar getDangerBar(){
        return this.dangerBar;
    }

}
