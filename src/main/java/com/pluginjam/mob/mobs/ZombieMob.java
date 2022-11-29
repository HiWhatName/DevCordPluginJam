package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Zombie;

public class ZombieMob extends DungeonMob<Zombie> {
    public ZombieMob(int level, boolean miniBoss) {
        super(level, Zombie.class, miniBoss);
    }


    @Override
    public String getDisplayName() {
        return "Zombie";
    }
}