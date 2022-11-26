package com.pluginjam.mob;

import org.bukkit.Location;
import org.bukkit.World;

public record SpawnMeta(Location location, World world) {
    public SpawnMeta(Location location) {
        this(location, location.getWorld());
    }
}
