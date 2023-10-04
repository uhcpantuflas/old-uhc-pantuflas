package com.github.gorkiiuss.uhcpantuflas.gameplay;

/**
 * The UHCGameMode enum represents different game modes available for Ultra Hardcore (UHC) matches.
 * These modes define various rules and settings for UHC gameplay.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public enum UHCGameMode {
    /**
     * The "Min Zero Teams" game mode starts with teams from the beginning of the match.
     * Players are organized into teams, and team gameplay is a key aspect of this mode.
     */
    MIN_ZERO_TEAMS;

    public static UHCGameMode get(int gameModeIdx) {
        return values()[gameModeIdx];
    }
}

