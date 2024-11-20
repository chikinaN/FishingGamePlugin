package com.chigayuki.minecraft.lib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FishInventory implements Listener {
  private final int Ymax = 6;
  private final int Xmax = 9;
  private final List<Point> points = new ArrayList<>();
  private final Inventory inventory;

  public FishInventory(String title, Player player) {
    this.inventory = Bukkit.createInventory(player, Xmax * Ymax, title);
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (event.getInventory().equals(inventory)) {
      event.setCancelled(true);
    }
  }

  private int calcPosition(Point point) {
    return point.y * Xmax + point.x;
  }

  private Point findPosition(int index) {
    return new Point(index % Xmax, index / Xmax);
  }

  public void AddItem(Point start, List<ItemStack> items) {
    int position = calcPosition(start);
    for (int i = 0; i < items.size(); i++) {
      SetItem(findPosition(position + i), items.get(i));
    }
  }

  public void SetItem(Point point, ItemStack item) {
    setPoints(point);
    inventory.setItem(calcPosition(point), item);
  }

  private void setPoints(Point point) {
    points.add(point);
  }

  public Inventory GenerateInventory() {
    setOtherItems();
    return inventory;
  }

  private void setOtherItems() {
    ItemStack otherItem = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
    ItemMeta meta = otherItem.getItemMeta();
    meta.setDisplayName("");
    otherItem.setItemMeta(meta);
    for (int y = 0; y < Ymax; y++) {
      for (int x = 0; x < Xmax; x++) {
        Point p = new Point(x, y);
        if (!points.contains(p)) {
          SetItem(p, otherItem);
        }
      }
    }
  }
}
