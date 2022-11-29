package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public class CreeperMob extends DungeonMob<Creeper> {

    public CreeperMob(int level, boolean miniBoss) {
        super(level, Creeper.class, miniBoss);
    }
    @Override
    public String getDisplayName() {
        return "Creeper";
    }
}
