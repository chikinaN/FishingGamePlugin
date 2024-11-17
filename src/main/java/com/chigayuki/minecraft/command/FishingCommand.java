package com.chigayuki.minecraft.command;

import com.chigayuki.minecraft.listeners.FishingListener;
import com.chigayuki.minecraft.util.CountDownBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FishingCommand implements CommandExecutor {
  private final JavaPlugin plugin;
  private Player player;

  public FishingCommand(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("このコマンドはプレイヤーのみ実行可能です。");
      return true;
    }
    Player player = (Player) sender;
    FishingListener fishingListener = new FishingListener();
    plugin.getServer().getPluginManager().registerEvents(fishingListener, plugin);
    new CountDownBar(
            "釣りミニゲーム",
            player,
//            600,
            60, // デバック用
            plugin,
            () -> {
              HandlerList.unregisterAll(fishingListener);
              player.sendMessage("釣りミニゲーム終了！");
            }
    );
    return false;
  }
}
