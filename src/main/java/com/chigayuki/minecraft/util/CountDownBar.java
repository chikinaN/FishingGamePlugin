package com.chigayuki.minecraft.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownBar {
  private final BossBar bossBar;
  private final Runnable onComplete;

  public CountDownBar(String title, Player player, int countDown, JavaPlugin plugin, Runnable onComplete) {
    this.onComplete = onComplete;
    bossBar = Bukkit.createBossBar(title + "残り時間", BarColor.RED, BarStyle.SEGMENTED_20);
    bossBar.addPlayer(player);
    startCountDown(plugin, countDown);
  }

  private void startCountDown(JavaPlugin plugin, int countDown) {
    new BukkitRunnable() {
      int timeLeft = countDown;

      @Override
      public void run() {
        if (timeLeft <= 0) {
          bossBar.removeAll();
          if (onComplete != null) {
            onComplete.run();
          }
          cancel();
          return;
        }
        bossBar.setProgress((double) timeLeft / countDown);
        timeLeft--;
      }
    }.runTaskTimer(plugin, 0, 20);
  }

  public void Remove() {
    bossBar.removeAll();
  }
}