package com.chigayuki.minecraft.lib;

import com.chigayuki.minecraft.util.Fish;
import com.chigayuki.minecraft.util.ItemRarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FishingItems {
  // デバック用のアイテム
  FISH_1(new Fish("さかな1", ItemRarity.COMMON, new ItemStack(Material.TROPICAL_FISH), "テスト魚", null)),
  FISH_2(new Fish("さかな2", ItemRarity.UNCOMMON, new ItemStack(Material.SALMON), "テスト魚", null)),
  FISH_3(new Fish("さかな3", ItemRarity.RARE, new ItemStack(Material.PUFFERFISH), "テスト魚", null)),
  FISH_4(new Fish("さかな4", ItemRarity.EPIC, new ItemStack(Material.COD), "テスト魚", null)),
  FISH_5(new Fish("さかな5", ItemRarity.LEGENDARY, new ItemStack(Material.COOKED_COD), "テスト魚", null)),
//  MEGALODON(new Fish("メガロドン", ItemRarity.LEGENDARY, new ItemStack(Material.DIAMOND_SWORD), "伝説の魚", null))
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

  public static FishingItems getRandomFish() {
    Map<ItemRarity, List<FishingItems>> itemsByRarity = Stream.of(values())
            .collect(Collectors.groupingBy(item -> item.getFish().getRarity()));

    int random = (int) (Math.random() * 100);

    ItemRarity selectedRarity = Arrays.stream(ItemRarity.values())
            .filter(rarity -> random >= rarity.GetChance())
            .max(Comparator.comparingInt(ItemRarity::GetChance))
            .orElse(ItemRarity.COMMON);

    List<FishingItems> items = itemsByRarity.get(selectedRarity);

    if (items == null || items.isEmpty()) {
      return values()[(int) (Math.random() * values().length)];
    }

    return items.get((int) (Math.random() * items.size()));
  }

  public static Fish getFishByName(String name) {
    for (FishingItems item : values()) {
      if (item.getFish().getName().equalsIgnoreCase(name)) {
        return item.getFish();
      }
    }
    return null;
  }

  public static Comparator<FishingItems> byRarity() {
    return Comparator.comparingInt(item -> item.getFish().getRarity().GetChance());
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