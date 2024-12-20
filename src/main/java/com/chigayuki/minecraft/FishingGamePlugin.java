package com.chigayuki.minecraft;

import com.chigayuki.minecraft.command.FishList;
import com.chigayuki.minecraft.command.FishingCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FishingGamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        FishingCommand fishingCommand = new FishingCommand(this);
        FishList fishList = new FishList();

        getCommand("fishing").setExecutor(fishingCommand);
        getCommand("fishList").setExecutor(fishList);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
