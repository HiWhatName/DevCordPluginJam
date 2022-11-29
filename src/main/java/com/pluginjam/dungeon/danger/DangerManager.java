package com.pluginjam.dungeon.danger;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;

public class DangerManager {
    private float dangerLevel;
    Difficulty difficulty;
    private DangerChangeEvent dangerChangeEvent;

    public DangerManager(Difficulty difficulty){
        this.difficulty = difficulty;
    }
    public float getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(float dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public float increaseDangerLevel(float increase) {
        this.dangerChangeEvent = new DangerChangeEvent(increase, dangerLevel);
        Bukkit.getServer().getPluginManager().callEvent(dangerChangeEvent);
        this.dangerLevel += increase * (1f + (difficulty.getValue() -1f) / 4); // // Deprecated for years, but returns 1/2/3 on easy/normal/hard e.g: A(Hard) = 50% higher lvl.
        return dangerLevel;
    }

    public float decreaseDangerLevel(float decrease) {
        this.dangerChangeEvent = new DangerChangeEvent(decrease, dangerLevel);
        Bukkit.getServer().getPluginManager().callEvent(dangerChangeEvent);
        this.dangerLevel -= decrease; // Adjusting to difficulty isn't necessary here
        return dangerLevel;
    }
}
