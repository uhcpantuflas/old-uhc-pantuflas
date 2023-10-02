package com.github.gorkiiuss.uhcpantuflas.text;

import java.util.ArrayList;

/**
 * The MinecraftTextBuilder class is used to construct complex Minecraft-formatted text strings.
 * It allows you to build text with various formatting options such as bold, strikethrough, obfuscated, and color.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public class MinecraftTextBuilder {
    private final ArrayList<MinecraftText> textList = new ArrayList<>();

    /**
     * Builds the constructed Minecraft-formatted text into a JSON string.
     *
     * @return The JSON representation of the constructed text.
     */
    public String build() {
        if (textList.isEmpty()) return "";

        StringBuilder builtMinecraftText = new StringBuilder();

        if (textList.size() == 1) {
            builtMinecraftText.append(textList.get(0));
        } else {
            builtMinecraftText.append("[\"\",");
            for (int i = 0; i < textList.size() - 1; i++) {
                builtMinecraftText.append(textList.get(i)).append(",");
            }

            builtMinecraftText.append(textList.get(textList.size() - 1));

            builtMinecraftText.append("]");
        }

        return builtMinecraftText.toString();
    }

    /**
     * Adds a new text element to the builder with default formatting attributes.
     *
     * @param text The text content to add.
     * @return The updated MinecraftTextBuilder instance.
     */
    public MinecraftTextBuilder addText(String text) {
        textList.add(new MinecraftText(text));
        return this;
    }

    /**
     * Applies the bold formatting to the last added text element.
     *
     * @return The updated MinecraftTextBuilder instance.
     */
    public MinecraftTextBuilder bold() {
        textList.get(textList.size() - 1).bold();
        return this;
    }

    /**
     * Applies the strikethrough formatting to the last added text element.
     *
     * @return The updated MinecraftTextBuilder instance.
     */
    public MinecraftTextBuilder strikethrough() {
        textList.get(textList.size() - 1).strikethrough();
        return this;
    }

    /**
     * Applies the obfuscated formatting to the last added text element.
     *
     * @return The updated MinecraftTextBuilder instance.
     */
    public MinecraftTextBuilder obfuscated() {
        textList.get(textList.size() - 1).obfuscated();
        return this;
    }

    /**
     * Sets the color of the last added text element.
     *
     * @param color The color to set.
     * @return The updated MinecraftTextBuilder instance.
     */
    public MinecraftTextBuilder color(MinecraftColor color) {
        textList.get(textList.size() - 1).color(color);
        return this;
    }

    /**
     * Sets the color of all text elements in the builder.
     *
     * @param color The color to set.
     * @return The updated MinecraftTextBuilder instance.
     */
    public MinecraftTextBuilder colorAll(MinecraftColor color) {
        for (MinecraftText text : textList) {
            text.color(color);
        }

        return this;
    }

    @Override
    public String toString() {
        return build();
    }
}
