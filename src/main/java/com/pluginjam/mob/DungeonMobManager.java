package com.pluginjam.mob;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

public class DungeonMobManager {

    //RandomCollection<String> rc = new RandomCollection<>().add(13,new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("Used miner's chest-plate").build()).add(
           // 16,new ItemBuilder(Material.LEATHER_LEGGINGS).setName("Used miner's leggings").build()).add(15, new ItemBuilder(Material.LEATHER_BOOTS).setName("Used miner's boots").build()).add(
                  //  15, new ItemBuilder(Material.LEATHER_BOOTS).setName("Used miner's boots").build()).add(8, new ItemBuilder(Material.LEATHER_HELMET).setName("Used miner's helmet").build()).add(63, new ItemStack().ROTTEN_FLESH);


    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof DungeonMob<?>){

        }
    }

}
