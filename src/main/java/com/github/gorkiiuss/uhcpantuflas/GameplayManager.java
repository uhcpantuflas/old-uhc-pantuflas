package com.github.gorkiiuss.uhcpantuflas;

public class GameplayManager {
    private static GameplayManager instance;
    private UHCGameMode gameMode;

    private GameplayManager() {

    }

    public static synchronized  GameplayManager get() {
        if (instance == null) instance = new GameplayManager();
        return instance;
    }

    public void setGameMode(UHCGameMode gameMode) {
        this.gameMode = gameMode;
        System.out.println("Game mode set to " + gameMode);
    }

    public UHCGameMode getGameMode() {
        return gameMode;
    }
}
