package com.pluginjam.core;

import org.bukkit.Material;

import static org.bukkit.Material.*;
import static org.bukkit.Material.DEEPSLATE_EMERALD_ORE;

public enum ArmorWeight {
    LEATHER( 0.005f),
    IRON(0.01f),
    GOLD(0.015f),
    DIAMOND( 0.0125f),
    NETHERITE(0.02f);

    private final float weight;
    ArmorWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight(){
        return weight;
    }
}
