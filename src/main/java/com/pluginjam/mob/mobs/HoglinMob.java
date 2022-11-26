package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Hoglin;
import org.jetbrains.annotations.NotNull;

public class HoglinMob extends DungeonMob<Hoglin> {
    public HoglinMob(int level, @NotNull Class<Hoglin> baseEntityClass) {
        super(level, baseEntityClass);
    }

    @Override
    public String getDisplayName() {
        return "Hoglin";
    }
}
