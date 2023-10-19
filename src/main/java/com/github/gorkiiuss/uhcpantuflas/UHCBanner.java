package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;

public class UHCBanner {
    public final DyeColor baseColor;
    private final Pattern[] patterns;

    public UHCBanner(DyeColor baseColor, Pattern[] patterns) {
        this.baseColor = baseColor;
        this.patterns = patterns;
    }

    public DyeColor getBaseColor() {
        return baseColor;
    }

    public Pattern[] getPatterns() {
        return patterns;
    }
}
