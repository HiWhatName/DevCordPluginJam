package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Zoglin;
import org.jetbrains.annotations.NotNull;

public class ZoglinMob extends DungeonMob<Zoglin> {
    @Override
    public String getDisplayName() {
        return "Zoglin";
    }

    public ZoglinMob(int level, boolean miniBoss) {
        super(level, Zoglin.class, miniBoss);
    }
}
