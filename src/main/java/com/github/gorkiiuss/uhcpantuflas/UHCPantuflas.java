package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.config.ConfigurationManager;
import com.github.gorkiiuss.uhcpantuflas.config.UHCConfigCommandExecutor;
import com.github.gorkiiuss.uhcpantuflas.config.UHCConfigTabCompleter;
import com.github.gorkiiuss.uhcpantuflas.player.PlayerJoinListener;
import com.github.gorkiiuss.uhcpantuflas.player.PlayerLoginListener;
import com.github.gorkiiuss.uhcpantuflas.teams.UHCTeamCommandExecutor;
import com.github.gorkiiuss.uhcpantuflas.teams.UHCTeamTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
 * @version 0.0.1-ALPHA.0 TODO 03/10/2023 change version name to ALPHA.1
 * @since 01/10/2023-INITIAL
 */
@SuppressWarnings("unused")
public final class UHCPantuflas extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load current configuration
        saveDefaultConfig();
        ConfigurationManager.get().init(getConfig());

        // Register all listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);

        // Register all commands
        Objects.requireNonNull(getCommand(UHCConfigCommandExecutor.NAME)).setExecutor(new UHCConfigCommandExecutor());
        Objects.requireNonNull(getCommand(UHCConfigCommandExecutor.NAME)).setTabCompleter(new UHCConfigTabCompleter());
        Objects.requireNonNull(getCommand(UHCTeamCommandExecutor.NAME)).setExecutor(new UHCTeamCommandExecutor());
        Objects.requireNonNull(getCommand(UHCTeamCommandExecutor.NAME)).setTabCompleter(new UHCTeamTabCompleter());

        System.out.println("Plugin UHC Pantuflas-0.0.0-INITIAL successfully charged");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
