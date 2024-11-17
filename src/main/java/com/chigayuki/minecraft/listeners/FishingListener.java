package com.chigayuki.minecraft.listeners;

import com.chigayuki.minecraft.lib.FishingItems;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class FishingListener implements Listener {
  @EventHandler
  public void OnPlayerFishEvent(PlayerFishEvent event) {
    Player player = event.getPlayer();
    player.sendMessage("釣りの状態: " + event.getState());
    switch (event.getState()) {
      case FISHING:
        FishHook hook = event.getHook();
        int maxWaitTime = hook.getMaxWaitTime();
        int LureBonus = getLureBonus(player, hook);
        hook.setMaxWaitTime(100 + Math.max(maxWaitTime / 3 - LureBonus, 0));
        hook.setMinWaitTime(10);
        break;
      case CAUGHT_FISH:
        ItemStack itemStack = FishingItems.getRandomFish().getItem();
        Item droppedItem = player.getWorld().dropItem(event.getHook().getLocation(), itemStack);
        droppedItem.setPickupDelay(0);
        applyVelocity(player, droppedItem);

        event.getHook().setHookedEntity(droppedItem);

        if (event.getCaught() != null) {
          event.getCaught().remove();
        }
        break;
      default:
        break;
    }
  }

  private void applyVelocity(Player player, Entity entity) {
    Vector origin = entity.getLocation().toVector();
    Vector target = player.getLocation().toVector();
    Vector direction = target.subtract(origin);
    double distance = Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getZ(), 2));
    double yBoost = Math.min(distance * 0.55, 3.0);
    double speedMultiplier = Math.min(distance * 0.11, 2.5);
    direction.setY(yBoost);
    entity.setVelocity(direction.normalize().multiply(speedMultiplier));
  }

  private int getLureBonus(Player player, FishHook fishHook) {
    ItemStack fishingRod = player.getInventory().getItemInMainHand();
    if (!fishingRod.getType().getKey().getKey().equalsIgnoreCase("fishing_rod")) {
      return 0;
    }
    int lureLevel = fishingRod.getEnchantmentLevel(Enchantment.LURE);
    if (lureLevel > 0) {
      fishHook.setApplyLure(false);
      return lureLevel * 100;
    }
    return 0;
  }
}
