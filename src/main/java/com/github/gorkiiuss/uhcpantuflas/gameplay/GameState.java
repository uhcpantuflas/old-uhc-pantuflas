package com.github.gorkiiuss.uhcpantuflas.gameplay;

/**
 * The GameState enum represents different states of a UHC (Ultra Hardcore) game.
 * It is used to track the current phase of the game, such as the beginning, middle, or end.
 *
 * @version 0.0.1-ALPHA.0
 * @since 08/10/2023
 */
public enum GameState {
    /**
     * Represents the beginning state of a UHC game.
     * This is typically the initial state when players are preparing for the game.
     */
    BEGINNING;

    /**
     * Gets the GameState corresponding to the provided game state index.
     *
     * @param gameStateIdx The index of the game state.
     * @return The GameState enum constant corresponding to the provided index.
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds.
     */
    public static GameState get(int gameStateIdx) {
        return values()[gameStateIdx];
    }
}
