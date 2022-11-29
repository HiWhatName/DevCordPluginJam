package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import com.pluginjam.mob.SpawnMeta;
import org.bukkit.entity.Warden;

public class WardenMob extends DungeonMob<Warden> {
    public WardenMob(int level) {
        super(level, Warden.class,  true); //A Warden should always be a miniBoss, so we'll parse true
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
