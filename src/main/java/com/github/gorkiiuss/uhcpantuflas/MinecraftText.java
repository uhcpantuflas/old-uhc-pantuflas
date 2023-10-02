package com.github.gorkiiuss.uhcpantuflas;

public class MinecraftText {
    private final String text;
    private boolean bold;
    private boolean strikethrough;
    private boolean obfuscated;
    private MinecraftColor color;

    public MinecraftText(String text, boolean strikethrough, boolean obfuscated, MinecraftColor color) {
        this.text = text;
        this.strikethrough = strikethrough;
        this.obfuscated = obfuscated;
        this.color = color;
    }

    public MinecraftText(String text) {
        this(text, false, false, MinecraftColor.WHITE);
    }

    public void bold() {
        bold = true;
    }

    public void strikethrough() {
        strikethrough = true;
    }

    public void obfuscated() {
        obfuscated = true;
    }

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
