package com.chigayuki.minecraft.lib;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.List;

public class FishInventory {
  private final int Ymax = 6;
  private final int Xmax = 9;
  private final Inventory inventory;

  public FishInventory(String title) {
    this.inventory = Bukkit.createInventory(null, Xmax * Ymax, title);
    addItem(new Point(0, 0), FishingItems.getFishList());
  }

  public Inventory getInventory() {
    return inventory;
  }

  private int calcPosition(Point point) {
    return point.y * Xmax + point.x;
  }

  private Point findPosition(int index) {
    return new Point(index % Xmax, index / Xmax);
  }

  private void setItem(Point point, ItemStack item) {
    inventory.setItem(calcPosition(point), item);
  }

  public void addItem(Point start, List<ItemStack> items) {
    int position = calcPosition(start);
    for (int i = 0; i < items.size(); i++) {
      setItem(findPosition(position + i), items.get(i));
    }
  }
}
