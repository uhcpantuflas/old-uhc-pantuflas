package com.github.gorkiiuss.uhcpantuflas.utils.text;

/**
 * The MinecraftColor enum represents different colors used in Minecraft's text formatting.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public enum MinecraftColor {
    WHITE,
    YELLOW,
    GRAY, RED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
