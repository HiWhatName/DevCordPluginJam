package com.pluginjam.core.weight;

import org.bukkit.Material;

import static org.bukkit.Material.*;
import static org.bukkit.Material.DEEPSLATE_EMERALD_ORE;

public enum ArmorWeight {
    LEATHER( 0.0025f),
    ELYTRA( 0.005f),
    CHAIN(0.008f),
    IRON(0.011f),
    DIAMOND( 0.013f),
    GOLD(0.015f),
    NETHERITE(0.02f);

    private final float weight;
    ArmorWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight(){
        return weight;
    }
}
