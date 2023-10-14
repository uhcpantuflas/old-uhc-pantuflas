package com.github.gorkiiuss.uhcpantuflas.player;

/**
 * The UHCPlayer class represents a player in the UHC (Ultra Hardcore) game mode.
 * It stores the player's name and provides methods to check if the player has a specific name.
 *
 * @version 0.0.1-ALPHA.0
 * @since 08/10/2023
 */
public final class UHCPlayer {

    private final String name;
    private boolean immobilized;

    /**
     * Constructs a UHCPlayer object with the specified player name.
     *
     * @param name The name of the player.
     */
    public UHCPlayer(String name) {
        this.name = name;
    }

    /**
     * Check if the UHCPlayer has a specific name.
     *
     * @param name The name to check against the player's name.
     * @return True if the player's name matches the specified name, otherwise false.
     */
    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public void immobilize() {
        immobilized = true;
        System.out.println("Player " + name + " has been immobilized");
    }

    public boolean isImmobilized() {
        return immobilized;
    }

    public String getName() {
        return name;
    }

    public void reset() {

    }

    public void setImmobilized(boolean immobilized) {
        this.immobilized = immobilized;
        System.out.println("Player " + name + " " + (immobilized? "can now move": "is immobilized"));
    }
}
