package com.chigayuki.minecraft.lib;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FishMaxScore extends FishInventory {
  public FishMaxScore(Player player, List<FishingItems> items) {
    super("最高スコア", player);
    AddItem(new Point(0, 0), countItems(items));
    SetItem(new Point(0, 4), new ItemStack(Material.DIAMOND, getScore(items)));
  }

  private static List<ItemStack> countItems(List<FishingItems> items) {
    Map<FishingItems, Integer> itemCountMap = new HashMap<>();
    for (FishingItems item : FishingItems.values()) {
      itemCountMap.put(item, 0);
    }
    for (FishingItems item : items) {
      itemCountMap.put(item, itemCountMap.get(item) + 1);
    }
    List<FishingItems> sortedItems = new ArrayList<>(itemCountMap.keySet());
    sortedItems.sort(FishingItems.byRarity());

    List<ItemStack> result = new ArrayList<>();
    for (FishingItems item : sortedItems) {
      int count = itemCountMap.get(item);
      if (count != 0) {
        ItemStack itemStack = item.getItem();
        itemStack.setAmount(count);
        result.add(itemStack);
      } else {
        ItemStack NONEItem = new ItemStack(Material.BARRIER);
        ItemMeta meta = NONEItem.getItemMeta();
        meta.setDisplayName("なし");
        meta.setLore(Collections.singletonList("釣れませんでした"));
        NONEItem.setItemMeta(meta);
        result.add(NONEItem);
      }
    }
    return result;
  }

  public int getScore(List<FishingItems> items) {
    int score = 0;
    for (FishingItems item : items) {
      score += item.getFish().getPoints();
    }
    return score;
  }
}
