package com.github.gorkiiuss.uhcpantuflas.text;

/**
 * The MinecraftText class represents text with optional formatting attributes used in Minecraft.
 * This class allows you to create formatted text for use in various Minecraft-related operations.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public class MinecraftText {
    private final String text;
    private boolean bold;
    private boolean strikethrough;
    private boolean obfuscated;
    private MinecraftColor color;

    /**
     * Constructs a MinecraftText object with the specified text and formatting attributes.
     *
     * @param text         The text content.
     * @param strikethrough Whether the text should be strikethrough.
     * @param obfuscated   Whether the text should be obfuscated.
     * @param color        The color of the text.
     */
    public MinecraftText(String text, boolean strikethrough, boolean obfuscated, MinecraftColor color) {
        this.text = text;
        this.strikethrough = strikethrough;
        this.obfuscated = obfuscated;
        this.color = color;
    }

    /**
     * Constructs a MinecraftText object with the specified text, using default formatting attributes.
     *
     * @param text The text content.
     */
    public MinecraftText(String text) {
        this(text, false, false, MinecraftColor.WHITE);
    }

    /**
     * Applies the bold formatting to the text.
     */
    public void bold() {
        bold = true;
    }

    /**
     * Applies the strikethrough formatting to the text.
     */
    public void strikethrough() {
        strikethrough = true;
    }

    /**
     * Applies the obfuscated formatting to the text.
     */
    public void obfuscated() {
        obfuscated = true;
    }

    /**
     * Sets the color of the text.
     *
     * @param color The color to set.
     */
    public void color(MinecraftColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "{" +
                "\"text\":\"" + text + "\"," +
                "\"bold\":\"" + bold + "\"," +
                "\"strikethrough\":\"" + strikethrough + "\"," +
                "\"obfuscated\":\"" + obfuscated + "\"," +
                "\"color\":\"" + color + "\"" +
                "}";
    }
}
