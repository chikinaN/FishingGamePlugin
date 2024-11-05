package com.chigayuki.minecraft.util;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class ItemLore {
  private final List<String> lore;

  public ItemLore(String rarity, int points, String description) {
    this.lore = Arrays.asList(
            "",
            rarity,
            generatePoints(points),
            "",
            generateDescription(description)
    );
  }

  public List<String> getLore() {
    return lore;
  }

  private String generatePoints(int points) {
    return ChatColor.GRAY + "Points: " + ChatColor.GOLD + points;
  }

  private String generateDescription(String description) {
    return ChatColor.GRAY + description;
  }
}
