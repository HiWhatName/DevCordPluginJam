package com.pluginjam.util;

import org.bukkit.inventory.ItemStack;

public interface RandomItem {
    void setProbability1(int probability);
    void setProbability2(int probability);


    int getProbability1();

    int getProbability2();

    ItemStack getItemStack();

    int getMinItems();

    int getMaxItems();

    int getProbability();
}
