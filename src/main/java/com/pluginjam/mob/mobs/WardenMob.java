package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import com.pluginjam.mob.SpawnMeta;
import org.bukkit.entity.Warden;

public class WardenMob extends DungeonMob<Warden> {
    public WardenMob(int level, Class<Warden> baseEntityClass) {
        super(level, baseEntityClass, false);
    }

    @Override
    public Warden spawn(SpawnMeta spawnMeta) {
        Warden warden = super.spawn(spawnMeta);
        warden.setHealth(40);

        return warden;
    }

    @Override
    public String getDisplayName() {
        return "Warden";
    }
}
