package com.chigayuki.minecraft.command;

import com.chigayuki.minecraft.lib.FishInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FishList implements CommandExecutor {
  private final String title = "釣れる魚の一覧";

  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("このコマンドはプレイヤーのみ実行可能です。");
      return true;
    }
    Inventory inventory = initiazizeInventory((Player) sender);
    return false;
  }

  private Inventory initiazizeInventory(Player player) {
    FishInventory fishInventory = new FishInventory(title);
    Inventory inventory = fishInventory.getInventory();
    player.openInventory(inventory);
    return inventory;
  }
}
