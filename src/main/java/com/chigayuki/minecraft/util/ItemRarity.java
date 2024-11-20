package com.chigayuki.minecraft.util;

import org.bukkit.ChatColor;

public enum ItemRarity {
  COMMON(ChatColor.WHITE, 10, 0),
  UNCOMMON(ChatColor.GREEN, 15, 40),
  RARE(ChatColor.BLUE, 25, 65),
  EPIC(ChatColor.DARK_PURPLE, 50, 85),
  LEGENDARY(ChatColor.GOLD, 100, 95);

  private final ChatColor color;
  private final int points;
  private final int chance;

  ItemRarity(ChatColor color, int points, int chance) {
    this.color = color;
    this.points = points;
    this.chance = chance;
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

  public int GetChance() {
    return chance;
  }
}
