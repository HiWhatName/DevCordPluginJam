package com.pluginjam.dungeon.danger;

import com.pluginjam.dungeon.DungeonOre;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DangerListener implements Listener {

    private final DangerManager dangerManager;

    public DangerListener(DangerManager dangerManager) {
        this.dangerManager = dangerManager;
    }

    @EventHandler
    public void onBreakOre(BlockBreakEvent event) {
        DungeonOre dungeonOre = DungeonOre.getFromMaterial(event.getBlock().getType());
        if (dungeonOre == null) {
            event.setCancelled(true);
        } else {
                dangerManager.increaseDangerLevel(dungeonOre.getDangerIncrease());
        }
    }


}
