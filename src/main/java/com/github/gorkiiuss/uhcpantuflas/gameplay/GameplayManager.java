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
    private GameState gameState;
    private boolean gameStateChanged;

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

    /**
     * Set the current game state.
     *
     * @param gameState The new game state to set.
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        this.gameStateChanged = true;
    }

    /**
     * Get the current game state.
     *
     * @return The current game state.
     */
    public GameState getGameState() {
        this.gameStateChanged = false;
        return gameState;
    }

    /**
     * Get if the game state has changed.
     *
     * @return If the current game state has changed.
     */
    public boolean hasGameStateChanged() {
        return gameStateChanged;
    }
}
