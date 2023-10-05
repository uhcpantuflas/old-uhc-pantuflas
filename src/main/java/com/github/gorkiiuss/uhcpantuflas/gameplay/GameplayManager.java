package com.github.gorkiiuss.uhcpantuflas.gameplay;

/**
 * The GameplayManager class is responsible for managing the gameplay of the UHC plugin.
 *
 * @version 0.0.1-ALPHA.0
 * @since 04/10/2023-ALPHA.0
 */
public class GameplayManager {
    private static GameplayManager instance;
    private UHCGameMode gameMode;

    private GameplayManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Get the instance of the GameplayManager.
     *
     * @return The GameplayManager instance.
     */
    public static synchronized  GameplayManager get() {
        if (instance == null) instance = new GameplayManager();
        return instance;
    }

    /**
     * Set the current game mode.
     *
     * @param gameMode The new game mode to set.
     */
    public void setGameMode(UHCGameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Get the current game mode.
     *
     * @return The current game mode.
     */
    public UHCGameMode getGameMode() {
        return gameMode;
    }
}
