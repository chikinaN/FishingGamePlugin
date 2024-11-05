package com.chigayuki.minecraft.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Fish {
  private final String name;
  private final ItemRarity rarity;
  private final ItemStack appearance;
  private final String description;
  private final Runnable specialEvent;
  private final ItemStack item;

  public Fish(String name, ItemRarity rarity, ItemStack appearance, String description, Runnable specialEvent) {
    this.name = name;
    this.rarity = rarity;
    this.appearance = appearance;
    this.description = description;
    this.specialEvent = specialEvent;
    this.item = setItem();
  }

  public String getName() {
    return name;
  }

  public String getRarity() {
    return rarity.name();
  }

  public int getPoints() {
    return rarity.GetPoints();
  }

  public ItemStack getAppearance() {
    return appearance;
  }

  public String getDescription() {
    return description;
  }

  public ItemStack getItem() {
    return item;
  }

  public void triggerSpecialEvent() {
    if (specialEvent != null) {
      specialEvent.run();
    }
  }

  private ItemStack setItem() {
    ItemStack item = new ItemStack(appearance);
    ItemMeta itemMeta = item.getItemMeta();
    itemMeta.setDisplayName(rarity.GenerateName(name));
    ItemLore lore = new ItemLore(
            rarity.GenerateRarity(),
            rarity.GetPoints(),
            description
    );
    itemMeta.setLore(lore.getLore());
    item.setItemMeta(itemMeta);
    return item;
  }
}