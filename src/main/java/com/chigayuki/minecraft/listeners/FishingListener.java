package com.chigayuki.minecraft.listeners;

import com.chigayuki.minecraft.lib.FishingItems;
import org.bukkit.entity.Entity;
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
    if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
      player.sendMessage("魚を釣り上げました。");
      ItemStack itemStack = FishingItems.getRandomFish().getItem();
      Item droppedItem = player.getWorld().dropItem(event.getHook().getLocation(), itemStack);
      droppedItem.setPickupDelay(0);
      applyVelocity(player, droppedItem);

      event.getHook().setHookedEntity(droppedItem);

      if (event.getCaught() != null) {
        event.getCaught().remove();
      }
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
}
