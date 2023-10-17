package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeManager {
    private static TimeManager instance;
    private UHCPantuflas plugin;

    private TimeManager() {

    }

    public void init(UHCPantuflas plugin) {
        this.plugin = plugin;
    }

    public static synchronized TimeManager get() {
        if (instance == null) instance = new TimeManager();
        return instance;
    }

    public void initTimers() {
        // Shrinking timer
        final int timeBeforeShrink = 60 * 60 * 3; // TODO: 15/10/2023 make configurable
        final int timeBeforeShrinkWarning = (timeBeforeShrink - (60 * 5));
        new BukkitRunnable() {
            @Override
            public void run() {
                TitleManager.get().sendTitle(TitleManager.BuiltInTitle.SHRINK_WARNING, TitleManager.TitlePosition.HOT_BAR);
            }
        }.runTaskLater(plugin, timeBeforeShrinkWarning * 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                TitleManager.get().sendTitle(TitleManager.BuiltInTitle.SHRINKING, TitleManager.TitlePosition.HOT_BAR);
                WorldManager.get().shrink();
            }
        }.runTaskLater(plugin, timeBeforeShrink * 20);

        // No PVP timer
        final int timeBeforeActivatingPVP = 60 * 45; // TODO: 16/10/2023 make configurable
        final int timeBeforeActivatingPVPWarning = (timeBeforeActivatingPVP - (60 * 5));
        new BukkitRunnable() {
            @Override
            public void run() {
                TitleManager.get().sendTitle(TitleManager.BuiltInTitle.PVP_WARNING, TitleManager.TitlePosition.HOT_BAR);
            }
        }.runTaskLater(plugin, timeBeforeActivatingPVPWarning * 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                TitleManager.get().sendTitle(TitleManager.BuiltInTitle.PVP, TitleManager.TitlePosition.HOT_BAR);
                GameplayManager.get().setPVP(true);
            }
        }.runTaskLater(plugin, timeBeforeActivatingPVP * 20);
    }
}
