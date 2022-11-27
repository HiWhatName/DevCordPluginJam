package com.pluginjam.util;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
/**
 * @author RGB__Toaster
 * @see ItemStack
 * @since 1.0.0
 */

@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class ItemBuilder{

    protected ItemStack item;
    protected ItemMeta meta;

    /**
     * creates an ItemBuilder
     *
     * @param material Item Material
     * @param amount   Item Amount
     */
    public ItemBuilder(Material material, byte amount) {
        if (material.isBlock() && material != Material.AIR)
            throw new IllegalArgumentException("Material Cannot be a Block!");

        item = new ItemStack(material, amount);
        meta = item.getItemMeta();
    }

    /**
     * creates an ItemBuilder
     *
     * @param material Item Material
     */
    public ItemBuilder(Material material) {
        this(material, (byte) 1);
    }

    /**
     * Empty Constructor
     */
    public ItemBuilder() {
        this(Material.AIR);
    }

    public static ItemBuilder newInstance() {
        return new ItemBuilder();
    }

    public static ItemBuilder newInstance(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder newInstance(Material material, byte amount) {
        return new ItemBuilder(material, amount);
    }

    /**
     * Sets the Item Name
     *
     * @param name {@link String}
     */
    public final ItemBuilder setName(String name) {
        meta.setDisplayName(name);

        return this;
    }


    /**
     * Sets the Lore
     *
     * @param lore Lore
     */
    public final ItemBuilder setLore(String... lore) {
        meta.setLore(List.of(lore));

        return this;
    }


    /**
     * Adds an Enchant to the Item
     *
     * @param enchant {@link Enchantment}
     * @param level   Enchantment Level
     */
    public final ItemBuilder addEnchant(Enchantment enchant, byte level) {
        meta.addEnchant(enchant, level, true);

        return this;
    }

    /**
     * Adds a Flag to the Item
     *
     * @param flags {@link ItemFlag}
     */
    public final ItemBuilder addFlag(ItemFlag... flags) {
        meta.addItemFlags(flags);

        return this;
    }

    /**
     * Sets the Item Unbreakable
     *
     * @param unbreakable Unbreakable flag
     */
    public final ItemBuilder setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);

        return this;
    }

    /**
     * Adds an {@link PersistentDataContainer}
     *
     * @param key   {@link NamespacedKey}
     * @param pdc   {@link PersistentDataType}
     * @param value DataType Value
     */
    public final <T, Z> ItemBuilder addPDC(NamespacedKey key, PersistentDataType<T, Z> pdc, Z value) {
        meta.getPersistentDataContainer().set(key, pdc, value);

        return this;
    }

    /**
     * Sets an Attribute to the Item
     *
     * @param attribute Item Attribute
     * @param mod       Attribute Modifier
     */
    public ItemBuilder addAttribute(Attribute attribute, AttributeModifier mod) {
        meta.addAttributeModifier(attribute, mod);

        return this;
    }

    /**
     * Sets the Item Type
     *
     * @param type Item Type
     */
    public ItemBuilder setType(Material type) {
        if (type.isBlock()) throw new IllegalArgumentException("Type Cannot be a Block!");

        item.setType(type);
        meta = item.getItemMeta();

        return this;
    }

    /**
     * Converts the ItemBuilder to a {@link ItemStack}
     *
     * @return {@link ItemStack}
     */
    public ItemStack build() {
        if (item.getType() == Material.AIR) throw new IllegalStateException("Type is not set !");

        item.setItemMeta(meta);

        return item;
    }

    /**
     * Adds an Unsafe Enchantment to the builder
     *
     * @param enchant Enchantment to Enchant
     * @param level   Enchantment Level
     * @return {@link ItemStack}
     */
    public ItemBuilder addUnsafeEnchant(Enchantment enchant, int level) {
        item.addUnsafeEnchantment(enchant, level);

        return this;
    }

}
