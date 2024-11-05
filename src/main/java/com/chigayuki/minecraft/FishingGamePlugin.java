package com.chigayuki.minecraft;

import com.chigayuki.minecraft.command.FishList;
import com.chigayuki.minecraft.command.FishingCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FishingGamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("fishing").setExecutor(new FishingCommand());
        getCommand("fishList").setExecutor(new FishList());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
