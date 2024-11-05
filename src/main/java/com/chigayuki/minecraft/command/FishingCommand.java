package com.chigayuki.minecraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FishingCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("このコマンドはプレイヤーのみ実行可能です。");
      return true;
    }
    sender.sendMessage("fishingコマンドが実行されました。");
    return false;
  }
}
