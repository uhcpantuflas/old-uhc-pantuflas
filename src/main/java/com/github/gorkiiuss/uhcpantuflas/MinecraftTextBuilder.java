package com.github.gorkiiuss.uhcpantuflas;

import java.util.ArrayList;

public class MinecraftTextBuilder {
    private final ArrayList<MinecraftText> textList = new ArrayList<>();

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

    public MinecraftTextBuilder addText(String text) {
        textList.add(new MinecraftText(text));
        return this;
    }

    public MinecraftTextBuilder bold() {
        textList.get(textList.size() - 1).bold();
        return this;
    }

    public MinecraftTextBuilder strikethrough() {
        textList.get(textList.size() - 1).strikethrough();
        return this;
    }

    public MinecraftTextBuilder obfuscated() {
        textList.get(textList.size() - 1).obfuscated();
        return this;
    }

    public MinecraftTextBuilder color(MinecraftColor color) {
        textList.get(textList.size() - 1).color(color);
        return this;
    }

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
