package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Zombie;

public class ZombieMob extends DungeonMob<Zombie> {
    public ZombieMob(int level) {
        super(level, Zombie.class);
    }


    @Override
    public String getDisplayName() {
        return "Zombie";
    }
}