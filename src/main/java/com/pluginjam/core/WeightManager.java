package com.pluginjam.core;

import com.sk89q.worldedit.world.item.ItemType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.UUID;

public class WeightManager implements Listener {

    private HashMap<UUID, Float> armorWeights = new HashMap<>();
    private static final float defaultPlayerSpeed = .2F;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!(armorWeights.containsKey(e.getPlayer().getUniqueId()))){
            armorWeights.put(e.getPlayer().getUniqueId(), 0.0f);
        }
    }

    @EventHandler
    public void onArmorEquip(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        armorWeights.put(p.getUniqueId(), calculateArmorWeight(p));
        p.setWalkSpeed(defaultPlayerSpeed - armorWeights.get(p.getUniqueId()));
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText(ChatColor.RED + String.valueOf(Math.round(armorWeights.get(p.getUniqueId()) * 1000 )) + "Kg"));
    }

    private float calculateArmorWeight(Player p){
        float weight = 0.0f;
        for (ItemStack armorPiece : p.getInventory().getArmorContents()) {
            if (armorPiece == null) continue;
            weight += getWeight(armorPiece);
        }
        return weight;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(!(e.getItem() == null)) {
            if (getWeight(e.getItem()) > 0) {
                armorWeights.put(p.getUniqueId(), calculateArmorWeight(p));
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(ChatColor.RED + String.valueOf(Math.round(armorWeights.get(p.getUniqueId()) * 1000)) + "Kg"));
                p.setWalkSpeed(defaultPlayerSpeed - armorWeights.get(p.getUniqueId()));
            }
        }
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