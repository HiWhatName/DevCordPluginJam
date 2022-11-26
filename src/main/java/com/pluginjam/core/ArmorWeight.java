package com.pluginjam.core;

import org.bukkit.Material;

import static org.bukkit.Material.*;
import static org.bukkit.Material.DEEPSLATE_EMERALD_ORE;

public enum ArmorWeight {
    LEATHER(LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, 0.05f),
    IRON(IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS, 0.75f),
    GOLD(GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS, 0.225f),
    DIAMOND(DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS, 0.15f),
    NETHERITE(NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS, 0.2f);

    private final float weight;
    ArmorWeight(Material helmet, Material chest, Material leggings, Material boots, float weight) {
        this.weight = weight;

    }
}
