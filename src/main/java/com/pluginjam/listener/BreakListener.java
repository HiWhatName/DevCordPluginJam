package com.pluginjam.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //TODO: Awaiting DungeonMob-AI integration. <- Change awareness if an ore is mined
    }

}
