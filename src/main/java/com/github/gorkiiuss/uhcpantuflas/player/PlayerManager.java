package com.github.gorkiiuss.uhcpantuflas.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

/**
 * The PlayerManager class is responsible for managing UHC (Ultra Hardcore) players in the plugin.
 * It provides methods to register players and check if a player is registered.
 *
 * @version 0.0.1-ALPHA.0
 * @since 08/10/2023
 */
public class PlayerManager {
    private static PlayerManager instance;
    private final ArrayList<UHCPlayer> players = new ArrayList<>();

    private PlayerManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Get the instance of the PlayerManager.
     *
     * @return The PlayerManager instance.
     */
    public static synchronized PlayerManager get() {
        if (instance == null) instance = new PlayerManager();
        return instance;
    }

    /**
     * Register a UHC player.
     *
     * @param player The UHCPlayer to register.
     */
    public void registerPlayer(UHCPlayer player) {
        players.add(player);
    }

    public void unregisterPlayer(String name) {
        players.removeIf(player -> player.hasName(name));
    }

    public void setImmobilized(boolean immobilized) {
        players.forEach( player -> player.setImmobilized(immobilized));

    }

    public boolean isPlayerImmobilized(String playerName) {
        UHCPlayer player = players.stream().filter(p -> p.hasName(playerName)).findFirst().orElse(null);
        return player != null && player.isImmobilized();
    }

    public void resetAll() {
        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();

        // Clear inventory
        server.dispatchCommand(
                sender,
                "clear @a"
        );

        // Clear XP
        server.dispatchCommand(
                sender,
                "xp set @a 0 points"
        );
        server.dispatchCommand(
                sender,
                "xp set @a 0 levels"
        );

        // Raise health
        server.dispatchCommand(
                sender,
                "effect give @a regeneration 1 255 true"
        );

        // Lower hunger
        server.dispatchCommand(
                sender,
                "effect give @a saturation 1 255 true"
        );
    }

    public void slowFallingAll() {
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "effect give @a slow_falling 10 1 true");
    }

    public int count() {
        return players.size();
    }

    public void changeGameMode(GameMode gameMode) {
        Server server = Bukkit.getServer();
        server.dispatchCommand(
                server.getConsoleSender(),
                "gamemode " + gameMode.name().toLowerCase() + " @a"
        );
    }

    public boolean isPlayerRegistered(String name) {
        return players.stream().anyMatch(player -> player.hasName(name));
    }
}
