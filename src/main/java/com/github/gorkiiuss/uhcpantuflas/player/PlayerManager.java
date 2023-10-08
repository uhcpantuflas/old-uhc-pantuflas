package com.github.gorkiiuss.uhcpantuflas.player;

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

    /**
     * Check if a player is registered.
     *
     * @param playerName The name of the player to check.
     * @return True if the player is registered, otherwise false.
     */
    public boolean isPlayerRegistered(String playerName) {
        return players.stream().anyMatch(uhcPlayer -> uhcPlayer.hasName(playerName));
    }
}
