package com.pluginjam.mob;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

public class DungeonMobManager {

    private void applyStatBoosts(DungeonMob dungeonMob, boolean special){

    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof DungeonMob<?>){

        }
    }

}
