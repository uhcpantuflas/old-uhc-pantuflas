package com.github.gorkiiuss.uhcpantuflas.player;

import java.util.ArrayList;

public class PlayerManager {
    private static PlayerManager instance;
    private final ArrayList<UHCPlayer> players = new ArrayList<>();

    private PlayerManager() {

    }

    public static synchronized PlayerManager get() {
        if (instance == null) instance = new PlayerManager();
        return instance;
    }

    public void registerPlayer(UHCPlayer player) {
        players.add(player);
    }

    public boolean isPlayerRegistered(String playerName) {
        return players.stream().anyMatch(uhcPlayer -> uhcPlayer.hasName(playerName));
    }
}
