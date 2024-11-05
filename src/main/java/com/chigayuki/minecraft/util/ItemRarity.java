package com.chigayuki.minecraft.util;

import org.bukkit.ChatColor;

public enum ItemRarity {
  COMMON(ChatColor.WHITE, 10),
  UNCOMMON(ChatColor.GREEN, 15),
  RARE(ChatColor.BLUE, 25),
  EPIC(ChatColor.DARK_PURPLE, 50),
  LEGENDARY(ChatColor.GOLD, 100);

  private final ChatColor color;
  private final int points;

  ItemRarity(ChatColor color, int points) {
    this.color = color;
    this.points = points;
  }

  public String GenerateName(String text) {
    return color + text;
  }

  public String GenerateRarity() {
    return color + name();
  }

  public int GetPoints() {
    return points;
  }
}
