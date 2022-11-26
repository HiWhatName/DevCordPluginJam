package com.pluginjam.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.UUID;

public class WeightManager implements Listener {

    private HashMap<UUID, Float> armorWeights = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!(armorWeights.containsKey(e.getPlayer().getUniqueId()))){
            armorWeights.put(e.getPlayer().getUniqueId(), 0.0f);
        }
    }

    @EventHandler
    public void onArmorEquip(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        PlayerInventory inv = p.getInventory();

        for (ItemStack armorPiece : inv.getArmorContents()) {
           if(armorPiece == null) continue;
           armorWeights.put(p.getUniqueId(), 0.0f + getWeight(armorPiece));
        }

        p.setWalkSpeed(0.2f - armorWeights.get(p.getUniqueId()));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem() == null) return;
        armorWeights.put(p.getUniqueId(), armorWeights.get(p.getUniqueId()) + getWeight(e.getItem()));
        p.setWalkSpeed(0.2f - armorWeights.get(p.getUniqueId()));
    }

    float getWeight(ItemStack armorPiece){
        return switch (armorPiece.getType()) {
            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS ->
                    ArmorWeight.LEATHER.getWeight();
            case IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS ->
                    ArmorWeight.IRON.getWeight();
            case GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS ->
                    ArmorWeight.GOLD.getWeight();
            case DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS ->
                    ArmorWeight.DIAMOND.getWeight();
            case NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS ->
                    ArmorWeight.NETHERITE.getWeight();
            default -> 0.0f;
        };
    }

}