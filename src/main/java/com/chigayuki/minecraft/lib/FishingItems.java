package com.chigayuki.minecraft.lib;

import com.chigayuki.minecraft.util.Fish;
import com.chigayuki.minecraft.util.ItemRarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum FishingItems {
  // デバック用のアイテム
  FISH_1(new Fish("さかな1", ItemRarity.COMMON, new ItemStack(Material.TROPICAL_FISH), "テスト魚", null)),
  FISH_2(new Fish("さかな2", ItemRarity.UNCOMMON, new ItemStack(Material.SALMON), "テスト魚", null)),
  FISH_3(new Fish("さかな3", ItemRarity.RARE, new ItemStack(Material.PUFFERFISH), "テスト魚", null)),
  ;

  private final Fish fish;

  FishingItems(Fish fish) {
    this.fish = fish;
  }

  public Fish getFish() {
    return fish;
  }

  public ItemStack getItem() {
    return fish.getItem();
  }

  public static List<ItemStack> getFishList() {
    List<ItemStack> itemStackList = new ArrayList<>();
    for (FishingItems item : values()) {
      itemStackList.add(item.getFish().getItem());
    }
    return itemStackList;
  }

  public static Fish getRandomFish() {
    return values()[(int) (Math.random() * values().length)].getFish();
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