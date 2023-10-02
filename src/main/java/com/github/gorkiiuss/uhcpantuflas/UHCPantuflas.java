package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * UHCPantuflas is a Minecraft plugin that allows players to create and customize
 * "Ultra Hardcore" (UHC) game mode matches on a Minecraft server.
 * <p>
 * UHC is a challenging game mode where players must survive in a world where
 * natural health regeneration is disabled. Players have various methods to regain
 * health artificially, including consuming golden apples or instant health potions.
 * Furthermore, once a player dies, they are eliminated from the game with no
 * possibility of respawning.
 * <p>
 * This plugin is designed to handle the core functionality of the UHC game mode
 * within the Minecraft server environment. It extends the JavaPlugin class,
 * allowing it to integrate seamlessly with the Bukkit/Spigot API.
 *
 * @version 0.0.1-ALPHA.0
 * @since 01/10/2023-INITIAL
 */
public final class UHCPantuflas extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load current configuration
        ConfigurationManager.get().init(this);

        // Register all listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        System.out.println("Plugin UHC Pantuflas-0.0.0-INITIAL successfully charged");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
