package com.github.gorkiiuss.uhcpantuflas.player;

public final class UHCPlayer {

    private final String name;

    public UHCPlayer(String name) {
        this.name = name;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }
}
