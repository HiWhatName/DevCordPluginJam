package com.pluginjam.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class ItemRandomizer {

    private int allProbabilityPoints;

    private final List<RandomItem> randomItems;

    public ItemRandomizer(List<RandomItem> randomItems) {
        this.randomItems = randomItems;
        calculateAllProbabilities();
    }

    public ItemStack getRandomItem() {
        ItemStack itemStack = null;
        Random random = new Random();

        int allPoints = allProbabilityPoints;
        int feastItemRandomInt = random.nextInt(allPoints);

        for (RandomItem randomItem : randomItems) {
            if (Util.isInBetween(feastItemRandomInt, randomItem.getProbability1(), randomItem.getProbability2())) {
                itemStack = randomItem.getItemStack();
                int min = randomItem.getMinItems();
                int max = randomItem.getMaxItems();
                int itemAmountRandomInt = random.nextInt((max - min) + 1) + min;
                itemStack.setAmount(itemAmountRandomInt);
                break;
            }
        }
        if (itemStack == null) {
            itemStack = new ItemStack(Material.AIR);
        }
        return itemStack;
    }

    private void calculateAllProbabilities() {
        for (RandomItem randomItem : randomItems) {
            allProbabilityPoints = randomItem.getProbability() + allProbabilityPoints;
        }
        for (int i = 0; i < randomItems.size(); i++) {
            RandomItem currentRandomItem = randomItems.get(i);
            if (i > 0) {
                RandomItem lastRandomItem = randomItems.get(i - 1);
                currentRandomItem.setProbability1(lastRandomItem.getProbability2() + 1);
            } else {
                currentRandomItem.setProbability1(0);
            }
            currentRandomItem.setProbability2(currentRandomItem.getProbability1() + currentRandomItem.getProbability() - 1);
        }
    }

}
