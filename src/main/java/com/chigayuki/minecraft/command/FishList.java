package com.chigayuki.minecraft.command;

import com.chigayuki.minecraft.lib.FishInventory;
import com.chigayuki.minecraft.lib.FishingItems;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;

public class FishList implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("このコマンドはプレイヤーのみ実行可能です。");
      return true;
    }
    Inventory inventory = initializeInventory((Player) sender);
    return false;
  }

  private Inventory initializeInventory(Player player) {
    FishInventory fishInventory = new FishInventory("釣れる魚の一覧", player);
    fishInventory.AddItem(new Point(0, 0), FishingItems.getFishList());
    Inventory inventory = fishInventory.GenerateInventory();
    player.openInventory(inventory);
    return inventory;
  }
}
