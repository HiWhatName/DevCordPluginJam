package com.pluginjam.dungeon;

import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import static org.bukkit.Material.*;

public enum DungeonOre {
    COAL(COAL_ORE, DEEPSLATE_COAL_ORE, 0.1f),
    IRON(IRON_ORE, DEEPSLATE_IRON_ORE, 0.1f),
    GOLD(GOLD_ORE, DEEPSLATE_GOLD_ORE, 0.2f),
    LAPIS(LAPIS_ORE, DEEPSLATE_LAPIS_ORE, 0.2f),
    DIAMOND(DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE, 0.3f),
    COPPER(COPPER_ORE, DEEPSLATE_COPPER_ORE, 0.05f),
    REDSTONE(REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE, 0.05f),
    EMERALD(EMERALD_ORE, DEEPSLATE_EMERALD_ORE, 0.1f);

    private final Material variant1;
    private final Material variant2;

    private final float dangerIncrease;
    DungeonOre(Material variant1, Material variant2, float dangerIncrease) {
        this.variant1 = variant1;
        this.variant2 = variant2;
        this.dangerIncrease = dangerIncrease;
    }

    private static final HashMap<Material, DungeonOre> bukkitMatToDungeonOre = new HashMap<>();

    static {
        DungeonOre[] ores = DungeonOre.values();
        for (DungeonOre ore : ores) {
            bukkitMatToDungeonOre.put(ore.variant1, ore);
            bukkitMatToDungeonOre.put(ore.variant2, ore);
        }
    }

    @Nullable
    public static DungeonOre getFromMaterial(Material material) { //TODO: Kinda always returns null
        return bukkitMatToDungeonOre.get(material);
    }

    public float getDangerIncrease() {
        return dangerIncrease;
    }
}
