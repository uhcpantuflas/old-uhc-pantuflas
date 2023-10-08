package com.github.gorkiiuss.uhcpantuflas.gameplay;

public enum GameState {
    BEGINNING;

    public static GameState get(int gameStateIdx) {
        return values()[gameStateIdx];
    }
}
