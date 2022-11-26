package com.pluginjam.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

public class RandomChest {

    private Location location;
    private ItemRandomizer itemRandomizer;
    private BlockFace blockFace;

    public RandomChest(Location location, BlockFace blockFace, ItemRandomizer itemRandomizer) {
        this.location = location;
        this.itemRandomizer = itemRandomizer;
        this.blockFace = blockFace;
    }

    public RandomChest build() {
        Block block = location.getBlock();
        block.setType(Material.CHEST);
        Chest chest = getFilledChest();
        org.bukkit.material.Chest materialData = new org.bukkit.material.Chest(blockFace);
        chest.setData(materialData);
        chest.update();
        return this;
    }

    private Chest getFilledChest() {
        BlockState state = location.getBlock().getState();
        Chest chest = null;
        if (state instanceof Chest) {
            chest = (Chest) state;
            Inventory chestInventory = chest.getBlockInventory();
            for (int i = 0; i < chestInventory.getSize(); i++) {
                chestInventory.setItem(i, this.itemRandomizer.getRandomItem());
            }
        }
        return chest;
    }


    public Location getLocation() {
        return location;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public ItemRandomizer getItemRandomizer() {
        return itemRandomizer;
    }
}
