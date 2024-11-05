package com.chigayuki.minecraft.lib;

import com.chigayuki.minecraft.util.Fish;
import com.chigayuki.minecraft.util.ItemRarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum FishingItems {
  GOLDEN_FISH(new Fish("Golden Fish", ItemRarity.COMMON, new ItemStack(Material.DIAMOND, 1), "A rare fish that brings fortune.", null)),
  MAGIC_CARP(new Fish("Magic Carp", ItemRarity.UNCOMMON, new ItemStack(Material.DIAMOND, 2), "A mystical fish with healing properties.", null)),
  CRYSTAL_TROUT(new Fish("Crystal Trout", ItemRarity.RARE, new ItemStack(Material.DIAMOND, 3), "A fish of crystal-clear beauty.", null));

  private final Fish fish;

  FishingItems(Fish fish) {
    this.fish = fish;
  }

  public Fish getFish() {
    return fish;
  }

  public static List<ItemStack> getFishList() {
    List<ItemStack> itemStackList = new ArrayList<>();
    for (FishingItems item : values()) {
      itemStackList.add(item.getFish().getItem());
    }
    return itemStackList;
  }

  public static Fish getFishByName(String name) {
    for (FishingItems item : values()) {
      if (item.getFish().getName().equalsIgnoreCase(name)) {
        return item.getFish();
      }
    }
    return null;
  }

  public static int getPointsForFish(String name) {
    Fish fish = getFishByName(name);
    return fish != null ? fish.getPoints() : 0;
  }

  public static void triggerSpecialEvent(String name) {
    Fish fish = getFishByName(name);
    if (fish != null) {
      fish.triggerSpecialEvent();
    }
  }
}